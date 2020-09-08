package com.morssscoding.transit.staticgtfs.dataproviders.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "STATICGTFS", name = "AGENCIES")
@Data
public class AgencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AGENCY_ID")
    private String agencyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "TIMEZONE")
    private String timezone;

    @Column(name = "LANGUAGE")
    private String lang;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FARE_URL")
    private String fareUrl;

    @Column(name = "EMAIL")
    private String email;
}
