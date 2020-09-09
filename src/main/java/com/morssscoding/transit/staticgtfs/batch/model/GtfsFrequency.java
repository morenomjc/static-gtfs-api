package com.morssscoding.transit.staticgtfs.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GtfsFrequency implements Serializable {

    public static final String NAME = "frequency";

    private String  trip_id;
    private String  start_time;
    private String  end_time;
    private Integer headway_secs;
    private Integer exact_times;

}
