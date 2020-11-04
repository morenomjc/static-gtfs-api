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
public class GtfsShape implements Serializable {

    public static final String NAME = "shape";

    private String  shape_id;
    private Double  shape_pt_lat;
    private Double  shape_pt_lon;
    private Integer shape_pt_sequence;
    private Double  shape_dist_traveled;

}
