package com.trips.trip.service;

import com.trips.trip.exception.TripNotFound;
import com.trips.trip.exception.UserNotFound;
import com.trips.trip.model.Trip;
import com.trips.trip.model.User;
import com.trips.trip.repository.TripRepository;
import com.trips.trip.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TripService {

    private final TripRepository tripRepo;
    private final UserRepository userRepo;

    public TripService(TripRepository tripRepo, UserRepository userRepo) {
        this.userRepo = userRepo;
        this.tripRepo = tripRepo;
    }

    public TripRepository repo(){ return tripRepo;}

    public ResponseEntity<Trip> addTrip(Trip trip, int userID){

        User user = userRepo.getReferenceById(userID);
        trip.setUser(user);


        return new  ResponseEntity<>(tripRepo.save(trip), HttpStatus.CREATED);
    }

    public ResponseEntity<Trip> findTrip(int tripID){

        Trip trip= tripRepo.findById(tripID)
                .orElseThrow(() -> new TripNotFound("No trip found for given trip ID"));

        return new  ResponseEntity<Trip>(trip, HttpStatus.OK);
    }

    public ResponseEntity<List<Trip>> getAllTrips(int userID){

        return new  ResponseEntity<List<Trip>>(tripRepo.findAllByUser_UserID(userID), HttpStatus.OK);
    }

    public ResponseEntity<Trip> updateTrip(Trip trip, int userID)
    {
        Trip dbTrip = tripRepo.findById(trip.getTripID())
                        .orElseThrow(() -> new TripNotFound("No trip found for given trip ID"));

        User user = userRepo.findById(userID)
                        .orElseThrow(() -> new UserNotFound("No user found for given user ID"));
        trip.setUser(user);
        return new  ResponseEntity<Trip>(tripRepo.save(trip), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTrip(int tripID) {

        Trip dbTrip = tripRepo.findById(tripID)
                .orElseThrow(() -> new TripNotFound("No trip found for given trip ID"));

        tripRepo.delete(dbTrip);

        return new  ResponseEntity<>("Trip deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAllTrips(int userID){

        User user = userRepo.findById(userID)
                .orElseThrow(() -> new UserNotFound("No user found for given user ID"));

        tripRepo.deleteAllByUser_UserID(userID);

        return new ResponseEntity<>("All trips deleted",HttpStatus.OK);
    }
}
