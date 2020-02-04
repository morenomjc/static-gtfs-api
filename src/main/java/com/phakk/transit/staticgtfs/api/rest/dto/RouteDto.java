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
public class RouteDto implements Serializable {

    @JsonProperty("route_id")
    private String id;

    @JsonProperty("agency_id")
    private String agency;

    @JsonProperty("route_short_name")
    private String shortName;

    @JsonProperty("route_long_name")
    private String longName;

    @JsonProperty("route_desc")
    private String desc;

    @JsonProperty("route_type")
    private DataTypeDto type;

    @JsonProperty("route_url")
    private String url;

    @JsonProperty("route_color")
    private String color;

    @JsonProperty("route_text_color")
    private String textColor;

    @JsonProperty("route_sort_order")
    private Integer sortOrder;
}
