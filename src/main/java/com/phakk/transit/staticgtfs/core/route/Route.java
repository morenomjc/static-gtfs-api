package com.phakk.transit.staticgtfs.core.route;

import com.phakk.transit.staticgtfs.core.constants.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    public static String TYPE = "routes";

    private String id;
    private String agency;
    private String shortName;
    private String longName;
    private String desc;
    private EnumValue type;
    private String url;
    private String color;
    private String textColor;
    private Integer sortOrder;

    @Getter
    @AllArgsConstructor
    public enum Fields {
        ROUTE_TYPE("route_type"),
        ;

        private String value;
    }
}
