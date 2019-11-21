package com.phakk.transit.staticgtfs.core.agency;

public class AgencyNotFoundException extends RuntimeException{
    public AgencyNotFoundException(String message) {
            super(message);
        }
}