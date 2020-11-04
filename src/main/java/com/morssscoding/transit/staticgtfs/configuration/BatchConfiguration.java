package com.morssscoding.transit.staticgtfs.configuration;

import com.morssscoding.transit.staticgtfs.batch.model.GtfsAgency;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsCalendar;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsFrequency;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsRoute;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsShape;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsStop;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsStopTime;
import com.morssscoding.transit.staticgtfs.batch.model.GtfsTrip;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsAgencyDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsCalendarDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsFileReader;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsFrequencyDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsRouteDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsShapeDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsStopDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsStopTimeDatabaseWriter;
import com.morssscoding.transit.staticgtfs.batch.steps.GtfsTripDatabaseWriter;
import com.morssscoding.transit.staticgtfs.configuration.properties.GtfsFileProperties;
import com.morssscoding.transit.staticgtfs.configuration.properties.GtfsFileProperty;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.agency.AgencyRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.calendar.CalendarRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.enumvalue.EnumValueRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.frequency.FrequencyRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.route.RouteEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.route.RouteRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.shape.ShapeRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stop.StopEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stop.StopRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.stoptime.StopTimeRepository;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.trip.TripEntityMapper;
import com.morssscoding.transit.staticgtfs.dataproviders.repository.trip.TripRepository;
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

    private static final String JOB_NAME                = "importGtfsData";

    private static final String AGENCY_STEP_NAME        = "parseAgencyFile";
    private static final String ROUTE_STEP_NAME         = "parseRouteFile";
    private static final String STOP_STEP_NAME          = "parseStopFile";
    private static final String CALENDAR_STEP_NAME      = "parseCalendarFile";
    private static final String TRIP_STEP_NAME          = "parseTripFile";
    private static final String STOPTIME_STEP_NAME      = "parseStopTimeFile";
    private static final String FREQUENCY_STEP_NAME     = "parseFrequencyFile";
    private static final String SHAPE_STEP_NAME         = "parseShapeFile";

    private static final String GTFS_AGENCY_WRITER      = "gtfsAgencyDatabaseWriter";
    private static final String GTFS_ROUTE_WRITER       = "gtfsRouteDatabaseWriter";
    private static final String GTFS_STOP_WRITER        = "gtfsStopDatabaseWriter";
    private static final String GTFS_CALENDAR_WRITER    = "gtfsCalendarWriter";
    private static final String GTFS_TRIP_WRITER        = "gtfsTripDatabaseWriter";
    private static final String GTFS_STOPTIME_WRITER    = "gtfsStopTimeDatabaseWriter";
    private static final String GTFS_FREQUENCY_WRITER   = "gtfsFrequencyDatabaseWriter";
    private static final String GTFS_SHAPE_WRITER       = "gtfsShapeDatabaseWriter";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final GtfsFileProperties gtfsFileProperties;

    @Bean(JOB_NAME)
    public Job importGtfsData(@Qualifier(AGENCY_STEP_NAME) Step parseAgencyFile,
                              @Qualifier(ROUTE_STEP_NAME) Step parseRouteFile,
                              @Qualifier(STOP_STEP_NAME) Step parseStopFile,
                              @Qualifier(CALENDAR_STEP_NAME) Step parseCalendarFile,
                              @Qualifier(TRIP_STEP_NAME) Step parseTripFile,
                              @Qualifier(STOPTIME_STEP_NAME) Step parseStopTimeFile,
                              @Qualifier(FREQUENCY_STEP_NAME) Step parseFrequencyFile,
                              @Qualifier(SHAPE_STEP_NAME) Step parseShapeFile) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(parseAgencyFile)
                .next(parseRouteFile)
                .next(parseStopFile)
                .next(parseCalendarFile)
                .next(parseTripFile)
                .next(parseStopTimeFile)
                .next(parseFrequencyFile)
                .next(parseShapeFile)
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
    public Step parseRouteFile(@Qualifier(GTFS_ROUTE_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(ROUTE_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsRoute.NAME, GtfsRoute.class))
                .writer(writer)
                .build();
    }

    @Bean(STOP_STEP_NAME)
    public Step parseStopFile(@Qualifier(GTFS_STOP_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(STOP_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsStop.NAME, GtfsStop.class))
                .writer(writer)
                .build();
    }

    @Bean(CALENDAR_STEP_NAME)
    public Step parseCalendarFile(@Qualifier(GTFS_CALENDAR_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(CALENDAR_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsCalendar.NAME, GtfsCalendar.class))
                .writer(writer)
                .build();
    }

    @Bean(TRIP_STEP_NAME)
    public Step parseTripFile(@Qualifier(GTFS_TRIP_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(TRIP_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsTrip.NAME, GtfsTrip.class))
                .writer(writer)
                .build();
    }

    @Bean(STOPTIME_STEP_NAME)
    public Step parseStopTimeFile(@Qualifier(GTFS_STOPTIME_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(STOPTIME_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsStopTime.NAME, GtfsStopTime.class))
                .writer(writer)
                .build();
    }

    @Bean(FREQUENCY_STEP_NAME)
    public Step parseFrequencyFile(@Qualifier(GTFS_FREQUENCY_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(FREQUENCY_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsFrequency.NAME, GtfsFrequency.class))
                .writer(writer)
                .build();
    }

    @Bean(SHAPE_STEP_NAME)
    public Step parseShapeFile(@Qualifier(GTFS_SHAPE_WRITER) ItemWriter<Object> writer){
        return stepBuilderFactory.get(SHAPE_STEP_NAME)
                .chunk(gtfsFileProperties.getChunks())
                .reader(buildGtfsFileReader(gtfsFileProperties.getSource(), GtfsShape.NAME, GtfsShape.class))
                .writer(writer)
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

    @Bean(GTFS_CALENDAR_WRITER)
    ItemWriter<?> gtfsTripCalendarWriter(CalendarRepository repository, CalendarEntityMapper mapper){
        return new GtfsCalendarDatabaseWriter(repository, mapper);
    }

    @Bean(GTFS_TRIP_WRITER)
    ItemWriter<?> gtfsTripDatabaseWriter(TripRepository repository, TripEntityMapper mapper, EnumValueRepository enumValueRepository){
        return new GtfsTripDatabaseWriter(repository, mapper, enumValueRepository);
    }

    @Bean(GTFS_SHAPE_WRITER)
    ItemWriter<?> gtfsShapeDatabaseWriter(ShapeRepository shapeRepository, ShapeEntityMapper shapeEntityMapper){
        return new GtfsShapeDatabaseWriter(shapeRepository, shapeEntityMapper);
    }

    @Bean(GTFS_STOPTIME_WRITER)
    ItemWriter<?> gtfsStopTimeDatabaseWriter(StopTimeRepository repository, StopTimeEntityMapper mapper, EnumValueRepository enumValueRepository){
        return new GtfsStopTimeDatabaseWriter(repository, mapper, enumValueRepository);
    }

    @Bean(GTFS_FREQUENCY_WRITER)
    ItemWriter<?> gtfsFrequencyDatabaseWriter(FrequencyRepository frequencyRepository, FrequencyEntityMapper frequencyEntityMapper, EnumValueRepository enumValueRepository){
        return new GtfsFrequencyDatabaseWriter(frequencyRepository, frequencyEntityMapper, enumValueRepository);
    }
}
