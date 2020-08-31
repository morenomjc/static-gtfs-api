package com.phakk.transit.staticgtfs.core.trip;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    public static String TYPE = "trips";

    private String routeId;
    private String serviceId;
    private String tripId;
    private String headsign;
    private String shortName;
    private EnumValue directionId;
    private String blockId;
    private String shapeId;
    private EnumValue wheelchairAccessible;
    private EnumValue bikesAllowed;

    @Getter
    @AllArgsConstructor
    public enum Fields {
        DIRECTION_ID("direction_id"),
        WHEELCHAIR_ACCESSIBLE("wheelchair_accessible"),
        BIKES_ALLOWED("bikes_allowed"),
        ;

        private final String value;
    }
}
