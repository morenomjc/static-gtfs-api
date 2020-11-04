package com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "STATICGTFS", name = "SHAPES")
@Data
public class ShapeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SHAPE_ID")
    private String shapeId;

    @Column(name = "LATITUDE")
    private Double lat;

    @Column(name = "LONGITUDE")
    private Double lon;

    @Column(name = "SEQUENCE")
    private Integer sequence;

    @Column(name = "DIST_TRAVELED")
    private Double distanceTraveled;
}
