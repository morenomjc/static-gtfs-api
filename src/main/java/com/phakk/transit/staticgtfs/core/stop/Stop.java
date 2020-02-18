package com.phakk.transit.staticgtfs.core.stop;

import com.phakk.transit.staticgtfs.core.constants.StopType;
import com.phakk.transit.staticgtfs.core.constants.WheelchairAccessibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

    private String id;
    private String code;
    private String name;
    private String desc;
    private Double lat;
    private Double lon;
    private String zoneId;
    private String url;
    private StopType type;
    private String parentStation;
    private String timezone;
    private WheelchairAccessibility wheelchairBoarding;
    private String levelId;
    private String platformCode;
}
