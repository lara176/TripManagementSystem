package com.trips.trip.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }

    public UserNotFound(){}
}
