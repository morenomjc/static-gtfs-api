package com.phakk.transit.staticgtfs.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopTimeDto implements Serializable {

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("arrival_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime arrivalTime;

    @JsonProperty("departure_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("stop_sequence")
    private Integer stopSequence;

    @JsonProperty("stop_headsign")
    private String stopHeadsign;

    @JsonProperty("pickup_type")
    private DataTypeDto pickupType;

    @JsonProperty("drop_off_type")
    private DataTypeDto dropOffType;

    @JsonProperty("shape_dist_traveled")
    private Double distanceTraveled;

    @JsonProperty("timepoint")
    private DataTypeDto timepoint;
}
