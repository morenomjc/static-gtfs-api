package com.morenomjc.transit.staticgtfs.core.route;

import com.morenomjc.transit.staticgtfs.core.constants.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteType {

    public static String TYPE = "route_types";

    private EnumValue type;
    private Integer count;

}
