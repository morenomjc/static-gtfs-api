package com.phakk.transit.staticgtfs.configuration;

import com.phakk.transit.staticgtfs.batch.model.Agency;
import com.phakk.transit.staticgtfs.batch.model.Route;
import com.phakk.transit.staticgtfs.batch.steps.AgencyGtfsDatabaseWriter;
import com.phakk.transit.staticgtfs.batch.steps.GtfsFileReader;
import com.phakk.transit.staticgtfs.batch.steps.RouteGtfsDatabaseWriter;
import com.phakk.transit.staticgtfs.configuration.properties.GtfsFileProperties;
import com.phakk.transit.staticgtfs.configuration.properties.GtfsFileProperty;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@EnableBatchProcessing
public class BatchConfiguration {

    private static final String JOB_NAME  = "importGtfsData";
    private static final String AGENCY_STEP_NAME = "parseAgencyFile";
    private static final String ROUTE_STEP_NAME = "parseRouteFile";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final GtfsFileProperties gtfsFileProperties;

    @Bean(JOB_NAME)
    public Job importGtfsData(@Qualifier(AGENCY_STEP_NAME) Step parseAgencyFile,
                              @Qualifier(ROUTE_STEP_NAME) Step parseRouteFile) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(parseAgencyFile)
                .next(parseRouteFile)
                .build();
    }

    @Bean(AGENCY_STEP_NAME)
    public Step parseAgencyFile(@Qualifier("agencyGtfsDatabaseWriter") ItemWriter agencyGtfsDatabaseWriter){
        return stepBuilderFactory.get(AGENCY_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), Agency.NAME, Agency.class))
                .writer(agencyGtfsDatabaseWriter)
                .build();
    }

    @Bean(ROUTE_STEP_NAME)
    public Step parseRouteFile(@Qualifier("routeGtfsDatabaseWriter") ItemWriter<Object> routeGtfsDatabaseWriter){
        return stepBuilderFactory.get(ROUTE_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), Route.NAME, Route.class))
                .writer(routeGtfsDatabaseWriter)
                .build();
    }

    ItemReader<?> buildGtfsFileReader(String source, String name, Class<?> target) {
        GtfsFileProperty gtfsFileProperty = gtfsFileProperties.getFiles().get(name);
        String fileName = source.concat(gtfsFileProperty.getFile());
        return new GtfsFileReader<>(fileName, target);
    }

    @Bean("agencyGtfsDatabaseWriter")
    ItemWriter<?> agencyGtfsDatabaseWriter(AgencyRepository repository, AgencyEntityMapper mapper){
        return new AgencyGtfsDatabaseWriter(repository, mapper);
    }

    @Bean("routeGtfsDatabaseWriter")
    ItemWriter<?> routeGtfsDatabaseWriter(RouteRepository repository, RouteEntityMapper mapper, EnumValueRepository enumValueRepository){
        return new RouteGtfsDatabaseWriter(repository, mapper, enumValueRepository);
    }
}
