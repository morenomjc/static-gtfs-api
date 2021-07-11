package com.morenomjc.transit.staticgtfs.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "gtfs.static")
public class GtfsFileProperties {
    private int chunks;
    private Map<String, GtfsFileProperty> files;
    private String source;
}


