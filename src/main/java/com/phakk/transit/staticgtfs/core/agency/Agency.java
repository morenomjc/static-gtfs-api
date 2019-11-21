package com.phakk.transit.staticgtfs.core.agency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agency {
    private String id;
    private String name;
    private String url;
    private String timezone;
    private String lang;
    private String phone;
    private String fareUrl;
    private String email;
}
