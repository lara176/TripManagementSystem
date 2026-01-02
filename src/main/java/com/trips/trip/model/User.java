package com.trips.trip.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User_Details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private int userID;

    @Column(unique = true , nullable = false)
    private String username;

    @NotBlank(message = "Enter a valid name")
    private String name;

    @Min(value=6000000000L , message = "Phone number should be 10 digits")
    @Max(value = 9999999999L , message = "Phone number should be 10 digits")
    @Column(nullable = false)
    private long phone;


    @NotBlank(message = "Please enter a valid password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Trip> trips=new ArrayList<>();
}
