package com.phakk.transit.staticgtfs.core.trip;

import com.phakk.transit.staticgtfs.core.constants.DropOffType;
import com.phakk.transit.staticgtfs.core.constants.PickupType;
import com.phakk.transit.staticgtfs.core.constants.Timepoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopTime {

    private String tripId;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private String stopId;
    private Integer stopSequence;
    private String stopHeadsign;
    private PickupType pickupType;
    private DropOffType dropOffType;
    private Double distanceTraveled;
    private Timepoint timepoint;
}
