package com.trips.trip.exception;

public class TripNotFound extends RuntimeException {

    public TripNotFound(){}


    public TripNotFound(String message) {
        super(message);
    }
}
