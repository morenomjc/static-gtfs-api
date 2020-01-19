package com.phakk.transit.staticgtfs.core.stop;

import com.phakk.transit.staticgtfs.core.constants.StopTypeEnum;
import com.phakk.transit.staticgtfs.core.constants.WheelchairBoardingEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stop implements Serializable {

    private String id;
    private String code;
    private String name;
    private String desc;
    private Double lat;
    private Double lon;
    private String zoneId;
    private String url;
    private StopTypeEnum type;
    private String parentStation;
    private String timezone;
    private WheelchairBoardingEnum wheelchairBoarding;
    private String levelId;
    private String platformCode;
}
