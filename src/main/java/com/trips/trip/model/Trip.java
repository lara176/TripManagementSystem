package com.trips.trip.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="Trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    private int tripID;

    @NotBlank(message = "Enter valid starting city name")
    private String fromCity;

    @NotBlank(message = "Enter valid destination city name")
    private String toCity;

    private Date date;

    private int fare;

    private int fuel;

    private int profit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userID",nullable = false)
    @JsonBackReference
    private User user;
}
