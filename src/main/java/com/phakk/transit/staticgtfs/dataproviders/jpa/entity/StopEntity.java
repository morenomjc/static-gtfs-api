package com.phakk.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOPS")
@Data
public class StopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stop_id")
    private String stopId;

    @Column(name = "code")
    private String stopCode;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "zone_id")
    private String zoneId;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String stopType;

    @Column(name = "parent_station")
    private String parentStation;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "wheelchair_boarding")
    private String wheelchairBoarding;

    @Column(name = "level_id")
    private String levelId;

    @Column(name = "platform_code")
    private String platformCode;
}
