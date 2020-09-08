package com.morssscoding.transit.staticgtfs.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GtfsAgency {
    public static final String NAME = "agency";

    private String agency_id;
    private String agency_name;
    private String agency_url;
    private String agency_timezone;
    private String agency_lang;
    private String agency_phone;
    private String agency_fare_url;
    private String agency_email;

}
