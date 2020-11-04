package com.morssscoding.transit.staticgtfs.core.shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shape {

    public static String TYPE = "shapes";

    private String  id;
    private Double  lat;
    private Double  lon;
    private Integer sequence;
    private Double  distanceTraveled;

}