package com.trips.trip.service;

import com.trips.trip.exception.UserNotFound;
import com.trips.trip.model.User;
import com.trips.trip.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<User> registerUser(User user){
        user.setPassword((encoder.encode(user.getPassword())));
        return new ResponseEntity<>(userRepo.save(user),HttpStatus.OK);
    }

    public ResponseEntity<Boolean> loginUser(String username , String password){

        Optional<User> optionalUser = userRepo.findByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(encoder.matches(password ,user.getPassword())){
                return new ResponseEntity<>(true,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
            }
        }else{
            throw new UserNotFound("No user found for given username");
        }
    }
}
