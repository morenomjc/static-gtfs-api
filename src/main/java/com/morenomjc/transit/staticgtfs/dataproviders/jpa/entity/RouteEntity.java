package com.morenomjc.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "STATICGTFS", name = "ROUTES")
@Data
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROUTE_ID")
    private String routeId;

    @Column(name = "AGENCY_ID")
    private String agency;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "LONG_NAME")
    private String longName;

    @Column(name = "DESCRIPTION")
    private String desc;

    @Column(name = "ROUTE_TYPE")
    private String type;

    @Column(name = "URL")
    private String url;

    @Column(name = "ROUTE_COLOR")
    private String color;

    @Column(name = "TEXT_COLOR")
    private String textColor;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;
}
