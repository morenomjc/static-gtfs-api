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
public class GtfsStopTime {

    public static final String NAME = "stoptime";

    private String  trip_id;
    private String  arrival_time;
    private String  departure_time;
    private String  stop_id;
    private Integer stop_sequence;
    private String  stop_headsign;
    private Integer pickup_type;
    private Integer drop_off_type;
    private Double  shape_dist_traveled;
    private Integer timepoint;

}
