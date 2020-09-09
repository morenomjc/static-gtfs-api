package com.morssscoding.transit.staticgtfs.core.frequency;

import com.morssscoding.transit.staticgtfs.core.constants.EnumValue;
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
public class Frequency {

    public static String TYPE = "frequencies";

    private String tripId;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration headwaySecs;
    private EnumValue exactTimes;

    @Getter
    @AllArgsConstructor
    public enum Fields {
        EXACT_TIMES("exact_times"),
        ;

        private String value;
    }
}
