package com.morssscoding.transit.staticgtfs.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GtfsRoute implements Serializable {

    public static final String NAME = "route";

    private String  route_id;
    private String  agency_id;
    private String  route_short_name;
    private String  route_long_name;
    private String  route_desc;
    private Integer route_type;
    private String  route_url;
    private String  route_color;
    private String  route_text_color;

}
