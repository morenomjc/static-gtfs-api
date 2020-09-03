package com.phakk.transit.staticgtfs.core.stop;

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
public class Stop {

    public static String TYPE = "stops";

    private String id;
    private String code;
    private String name;
    private String desc;
    private Double lat;
    private Double lon;
    private String zoneId;
    private String url;
    private EnumValue type;
    private String parentStation;
    private String timezone;
    private EnumValue wheelchairBoarding;
    private String levelId;
    private String platformCode;

    @Getter
    @AllArgsConstructor
    public enum Fields {
        STOP_TYPE("location_type"),
        WHEELCHAIR_BOARDING("wheelchair_boarding"),
        ;

        private String value;
    }
}
