package com.phakk.transit.staticgtfs.core.route;

import com.phakk.transit.staticgtfs.core.constants.RouteTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route implements Serializable {

    private String id;
    private String agency;
    private String shortName;
    private String longName;
    private String desc;
    private RouteTypeEnum type;
    private String url;
    private String color;
    private String textColor;
    private Integer sortOrder;
}
