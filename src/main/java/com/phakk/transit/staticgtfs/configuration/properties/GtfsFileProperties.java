package com.phakk.transit.staticgtfs.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "gtfs.static")
public class GtfsFileProperties {
    Map<String, GtfsFileProperty> files;
    private String source;
}


