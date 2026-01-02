package com.trips.trip.exception;


import com.trips.trip.response.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(TripNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleTripNotFound(TripNotFound ex){

        ResponseStructure<String> structure = new ResponseStructure<>();

        structure.setMessage(ex.getMessage());
        structure.setStatus(HttpStatus.BAD_REQUEST.value());
        structure.setData("Try with another trip ID");

        return new ResponseEntity<>(structure,HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleUserNotFound(UserNotFound ex){
        ResponseStructure<String> structure = new ResponseStructure<>();

        structure.setMessage(ex.getMessage());
        structure.setStatus(HttpStatus.BAD_REQUEST.value());
        structure.setData("Try with another user ID");

        return new ResponseEntity<>(structure,HttpStatus.BAD_GATEWAY);
    }
}
