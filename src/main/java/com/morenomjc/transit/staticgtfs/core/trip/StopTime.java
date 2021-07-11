package com.morenomjc.transit.staticgtfs.core.trip;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopTime {

    public static String TYPE = "stop_times";

    private String tripId;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private String stopId;
    private Integer stopSequence;
    private String stopHeadsign;
    private EnumValue pickupType;
    private EnumValue dropOffType;
    private Double distanceTraveled;
    private EnumValue timepoint;


    @Getter
    @AllArgsConstructor
    public enum Fields {
        PICKUP_TYPE("pickup_type"),
        DROP_OFF_TYPE("drop_off_type"),
        TIMEPOINT("timepoint"),
        ;

        private String value;
    }
}
