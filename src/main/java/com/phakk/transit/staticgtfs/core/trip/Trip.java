package com.phakk.transit.staticgtfs.core.trip;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowed;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    private String routeId;
    private String serviceId;
    private String tripId;
    private String headsign;
    private String shortName;
    private String directionId;
    private String blockId;
    private String shapeId;
    private WheelchairAccessibility wheelchairAccessible;
    private BikesAllowed bikesAllowed;

}
