package com.morenomjc.transit.staticgtfs.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GtfsStop implements Serializable {

    public static final String NAME = "stop";

    private String  stop_id;
    private String  stop_code;
    private String  stop_name;
    private String  stop_desc;
    private Double  stop_lat;
    private Double  stop_lon;
    private String  zone_id;
    private String  stop_url;
    private Integer location_type;
    private String  parent_station;
    private String  stop_timezone;
    private Integer wheelchair_boarding;

}
