package com.phakk.transit.staticgtfs.core.route;

import com.phakk.transit.staticgtfs.core.constants.RouteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private String id;
    private String agency;
    private String shortName;
    private String longName;
    private String desc;
    private RouteType type;
    private String url;
    private String color;
    private String textColor;
    private Integer sortOrder;
}
