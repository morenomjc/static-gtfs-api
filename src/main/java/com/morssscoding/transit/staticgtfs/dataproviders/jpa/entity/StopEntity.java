package com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "STATICGTFS", name = "STOPS")
@Data
public class StopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STOP_ID")
    private String stopId;

    @Column(name = "CODE")
    private String stopCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String desc;

    @Column(name = "LATITUDE")
    private Double lat;

    @Column(name = "LONGITUDE")
    private Double lon;

    @Column(name = "ZONE_ID")
    private String zoneId;

    @Column(name = "URL")
    private String url;

    @Column(name = "LOCATION_TYPE")
    private String stopType;

    @Column(name = "PARENT_STATION")
    private String parentStation;

    @Column(name = "TIMEZONE")
    private String timezone;

    @Column(name = "WHEELCHAIR_BOARDING")
    private String wheelchairBoarding;

    @Column(name = "LEVEL_ID")
    private String levelId;

    @Column(name = "PLATFORM_CODE")
    private String platformCode;
}
