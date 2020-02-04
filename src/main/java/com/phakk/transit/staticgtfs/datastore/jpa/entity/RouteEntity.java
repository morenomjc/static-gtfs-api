package com.phakk.transit.staticgtfs.datastore.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTES")
@Data
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id")
    private String routeId;

    @Column(name = "agency")
    private String agency;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @Column(name = "desc")
    private String desc;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "color")
    private String color;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
