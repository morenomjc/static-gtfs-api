package com.phakk.transit.staticgtfs.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDto implements Serializable {

    @JsonProperty("route_id")
    private String routeId;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("trip_headsign")
    private String headsign;

    @JsonProperty("trip_short_name")
    private String shortName;

    @JsonProperty("direction_id")
    private DataTypeDto directionId;

    @JsonProperty("block_id")
    private String blockId;

    @JsonProperty("shape_id")
    private String shapeId;

    @JsonProperty("wheelchair_accessible")
    private DataTypeDto wheelchairAccessible;

    @JsonProperty("bikes_allowed")
    private DataTypeDto bikesAllowed;


}
