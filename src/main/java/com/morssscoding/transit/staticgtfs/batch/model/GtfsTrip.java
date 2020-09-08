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
public class GtfsTrip {

    public static final String NAME = "trip";

    private String  route_id;
    private String  service_id;
    private String  trip_id;
    private String  trip_headsign;
    private String  trip_short_name;
    private Integer direction_id;
    private String  block_id;
    private String  shape_id;
    private Integer wheelchair_accessible;
    private Integer bikes_allowed;

}
