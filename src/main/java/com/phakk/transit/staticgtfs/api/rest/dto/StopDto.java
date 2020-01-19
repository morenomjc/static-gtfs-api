package com.phakk.transit.staticgtfs.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopDto implements Serializable {

    @JsonProperty("stop_id")
    private String id;

    @JsonProperty("stop_code")
    private String code;

    @JsonProperty("stop_name")
    private String name;

    @JsonProperty("stop_desc")
    private String desc;

    @JsonProperty("stop_lat")
    private Double lat;

    @JsonProperty("stop_lon")
    private Double lon;

    @JsonProperty("zone_id")
    private String zoneId;

    @JsonProperty("stop_url")
    private String url;

    @JsonProperty("location_type")
    private DataTypeDto locationType;

    @JsonProperty("parent_station")
    private String parentStation;

    @JsonProperty("stop_timezone")
    private String stopTimezone;

    @JsonProperty("wheelchair_boarding")
    private DataTypeDto wheelchairBoarding;

    @JsonProperty("level_id")
    private String levelId;

    @JsonProperty("platform_code")
    private String platformCode;
}
