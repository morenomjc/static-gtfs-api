package com.morenomjc.transit.staticgtfs.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrequencyDto {

    public static String TYPE = "frequencies";

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @JsonProperty("headway_secs")
    private Duration headwaySecs;

    @JsonProperty("exact_times")
    private DataTypeDto exactTimes;

    @Getter
    @AllArgsConstructor
    public enum Fields {
        EXACT_TIMES("exact_times"),
        ;

        private String value;
    }
}
