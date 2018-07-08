package org.upgrad.controllers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.models.User;
import org.upgrad.services.UserService;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<?> userSignUp(@RequestParam(value="firstName") String firstName,
                                     @RequestParam(value="lastName", defaultValue = "null") String lastName,
                                     @RequestParam(value="username") String username,
                                     @RequestParam(value="email") String email,
                                     @RequestParam(value="password") String password,
                                     @RequestParam(value="country") String country,
                                     @RequestParam(value="aboutMe", defaultValue = "null") String aboutMe,
                                     @RequestParam(value="dateOfBirth") Date dob,
                                     @RequestParam(value="phoneNumber", defaultValue = "null") String phoneNumber) {

        if (userService.findUserByUsername(username) != null) {
            return new ResponseEntity<>("Try any other Username, " +
                    "this Username has already been taken.", HttpStatus.FORBIDDEN);
        }

        if (userService.findUserByEmail(email) != null) {
            return new ResponseEntity<>("This user has already been registered, " +
                    "try with any other emailId.", HttpStatus.FORBIDDEN);
        }

        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        userService.addUser(username, sha256hex, email, firstName, lastName, aboutMe, dob, phoneNumber, country);

        return new ResponseEntity<>(username + " successfully registered", HttpStatus.OK);
    }
}
