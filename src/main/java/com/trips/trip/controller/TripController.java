package com.trips.trip.controller;


import com.trips.trip.model.Trip;
import com.trips.trip.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/{userID}")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip, @PathVariable int userID){
        return tripService.addTrip(trip,userID);
    }

    @GetMapping
    public ResponseEntity<Trip> findTrip(@RequestParam int tripID){
        return tripService.findTrip(tripID);
    }

    @GetMapping("/all/{userID}")
    public ResponseEntity<List<Trip>> findAllTrips(@PathVariable int userID){
        return tripService.getAllTrips(userID);
    }

    @DeleteMapping("/{tripID}")
    public ResponseEntity<String> deleteTrip(@PathVariable int tripID){

        return tripService.deleteTrip(tripID);
    }

    @DeleteMapping("/all/{userID}")
    public ResponseEntity<String> deleteAllTrips(@PathVariable int userID){

        return tripService.deleteAllTrips(userID);
    }

    @PutMapping
    public ResponseEntity<Trip> updateTrip(@RequestBody Trip trip, int userID){
        return tripService.updateTrip(trip,userID);
    }
}
