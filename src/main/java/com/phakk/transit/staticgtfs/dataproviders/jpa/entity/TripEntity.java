package com.phakk.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRIPS")
@Data
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id")
    private String routeId;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "trip_id")
    private String tripId;

    @Column(name = "headsign")
    private String headsign;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "direction_id")
    private String directionId;

    @Column(name = "block_id")
    private String blockId;

    @Column(name = "shape_id")
    private String shapeId;

    @Column(name = "wheelchair_accessible")
    private String wheelchairAccessible;

    @Column(name = "bikes_allowed")
    private String bikesAllowed;
}
