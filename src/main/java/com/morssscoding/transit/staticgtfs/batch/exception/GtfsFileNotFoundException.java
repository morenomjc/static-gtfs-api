package com.morssscoding.transit.staticgtfs.batch.exception;

public class GtfsFileNotFoundException extends RuntimeException {

    public GtfsFileNotFoundException() {
    }

    public GtfsFileNotFoundException(String message) {
        super(message);
    }
}
