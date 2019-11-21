package com.phakk.transit.staticgtfs.datastore.entity.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENCY")
@Data
public class AgencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agency_id")
    private String agencyId;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "language")
    private String lang;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fare_url")
    private String fareUrl;

    @Column(name = "email")
    private String email;
}
