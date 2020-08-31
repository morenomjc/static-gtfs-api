package com.phakk.transit.staticgtfs.configuration;

import com.phakk.transit.staticgtfs.batch.model.Agency;
import com.phakk.transit.staticgtfs.batch.steps.AgencyGtfsDatabaseWriter;
import com.phakk.transit.staticgtfs.batch.steps.GtfsFileReader;
import com.phakk.transit.staticgtfs.configuration.properties.GtfsFileProperties;
import com.phakk.transit.staticgtfs.configuration.properties.GtfsFileProperty;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.phakk.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
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
    private static final String STEP_NAME = "parseGtfsFiles";

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private GtfsFileProperties gtfsFileProperties;

    @Bean(JOB_NAME)
    public Job importGtfsData(@Qualifier(STEP_NAME) Step parseAgencyFile) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(parseAgencyFile)
                .build();
    }

    @Bean(STEP_NAME)
    public Step parseAgencyFile(@Qualifier("agencyGtfsDatabaseWriter") ItemWriter agencyGtfsDatabaseWriter){
        return stepBuilderFactory.get(STEP_NAME)
                .chunk(100)
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), Agency.NAME, Agency.class))
                .writer(agencyGtfsDatabaseWriter)
                .build();
    }

    ItemReader<?> buildGtfsFileReader(String source, String name, Class<?> target) {
        GtfsFileProperty gtfsFileProperty = gtfsFileProperties.getFiles().get(name);
        String fileName = source.concat(gtfsFileProperty.getFile());
        return new GtfsFileReader<>(fileName, target);
    }

    @Bean("agencyGtfsDatabaseWriter")
    ItemWriter<?> agencyGtfsDatabaseWriter(AgencyRepository repository, AgencyEntityMapper agencyEntityMapper){
        return new AgencyGtfsDatabaseWriter(repository, agencyEntityMapper);
    }
}
