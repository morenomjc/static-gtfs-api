package com.phakk.transit.staticgtfs.core.trip;

import com.phakk.transit.staticgtfs.core.constants.BikesAllowedEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibilityEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip implements Serializable {

    private String routeId;
    private String serviceId;
    private String tripId;
    private String headsign;
    private String shortName;
    private String directionId;
    private String blockId;
    private String shapeId;
    private WheelchairAccessibilityEnum wheelchairAccessible;
    private BikesAllowedEnum bikesAllowed;

}
