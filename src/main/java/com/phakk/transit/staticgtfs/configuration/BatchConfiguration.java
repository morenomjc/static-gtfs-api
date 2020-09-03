package com.phakk.transit.staticgtfs.configuration;

import com.phakk.transit.staticgtfs.batch.model.GtfsAgency;
import com.phakk.transit.staticgtfs.batch.model.GtfsRoute;
import com.phakk.transit.staticgtfs.batch.model.GtfsStop;
import com.phakk.transit.staticgtfs.batch.steps.GtfsAgencyDatabaseWriter;
import com.phakk.transit.staticgtfs.batch.steps.GtfsFileReader;
import com.phakk.transit.staticgtfs.batch.steps.GtfsRouteDatabaseWriter;
import com.phakk.transit.staticgtfs.batch.steps.GtfsStopDatabaseWriter;
import com.phakk.transit.staticgtfs.configuration.properties.GtfsFileProperties;
import com.phakk.transit.staticgtfs.configuration.properties.GtfsFileProperty;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
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
    private static final String STOP_STEP_NAME = "parseStopFile";

    private static final String GTFS_AGENCY_WRITER  = "gtfsAgencyDatabaseWriter";
    private static final String GTFS_ROUTE_WRITER   = "gtfsRouteDatabaseWriter";
    private static final String GTFS_STOP_WRITER   = "gtfsStopDatabaseWriter";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final GtfsFileProperties gtfsFileProperties;

    @Bean(JOB_NAME)
    public Job importGtfsData(@Qualifier(AGENCY_STEP_NAME) Step parseAgencyFile,
                              @Qualifier(ROUTE_STEP_NAME) Step parseRouteFile,
                              @Qualifier(STOP_STEP_NAME) Step parseStopFile) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(parseAgencyFile)
                .next(parseRouteFile)
                .next(parseStopFile)
                .build();
    }

    @Bean(AGENCY_STEP_NAME)
    public Step parseAgencyFile(@Qualifier(GTFS_AGENCY_WRITER) ItemWriter<Object> gtfsAgencyDatabaseWriter){
        return stepBuilderFactory.get(AGENCY_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsAgency.NAME, GtfsAgency.class))
                .writer(gtfsAgencyDatabaseWriter)
                .build();
    }

    @Bean(ROUTE_STEP_NAME)
    public Step parseRouteFile(@Qualifier(GTFS_ROUTE_WRITER) ItemWriter<Object> gtfsRouteDatabaseWriter){
        return stepBuilderFactory.get(ROUTE_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsRoute.NAME, GtfsRoute.class))
                .writer(gtfsRouteDatabaseWriter)
                .build();
    }

    @Bean(STOP_STEP_NAME)
    public Step parseStopFile(@Qualifier(GTFS_STOP_WRITER) ItemWriter<Object> gtfsStopDatabaseWriter){
        return stepBuilderFactory.get(STOP_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsStop.NAME, GtfsStop.class))
                .writer(gtfsStopDatabaseWriter)
                .build();
    }

    ItemReader<?> buildGtfsFileReader(String source, String name, Class<?> target) {
        GtfsFileProperty gtfsFileProperty = gtfsFileProperties.getFiles().get(name);
        String fileName = source.concat(gtfsFileProperty.getFile());
        return new GtfsFileReader<>(fileName, target);
    }

    @Bean(GTFS_AGENCY_WRITER)
    ItemWriter<?> gtfsAgencyDatabaseWriter(AgencyRepository repository, AgencyEntityMapper mapper){
        return new GtfsAgencyDatabaseWriter(repository, mapper);
    }

    @Bean(GTFS_ROUTE_WRITER)
    ItemWriter<?> gtfsRouteDatabaseWriter(RouteRepository repository, RouteEntityMapper mapper, EnumValueRepository enumValueRepository){
        return new GtfsRouteDatabaseWriter(repository, mapper, enumValueRepository);
    }

    @Bean(GTFS_STOP_WRITER)
    ItemWriter<?> gtfsStopDatabaseWriter(StopRepository repository, StopEntityMapper mapper, EnumValueRepository enumValueRepository){
        return new GtfsStopDatabaseWriter(repository, mapper, enumValueRepository);
    }

}
