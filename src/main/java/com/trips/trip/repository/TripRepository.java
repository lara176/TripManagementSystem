package com.trips.trip.repository;

import com.trips.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip,Integer> {


    List<Trip> findAllByUser_UserID(int userID);


    void deleteAllByUser_UserID(int userID);
}
