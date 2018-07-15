package org.upgrad.controllers;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.models.UserProfile;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/signup")
    public ResponseEntity<?> userSignUp(@RequestParam(value = "firstName") String firstName,
                                     @RequestParam(value = "lastName", defaultValue = "null") String lastName,
                                     @RequestParam(value = "username") String username,
                                     @RequestParam(value = "email") String email,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "country") String country,
                                     @RequestParam(value = "aboutMe", defaultValue = "null") String aboutMe,
                                     @RequestParam(value = "dateOfBirth") Date dob,
                                     @RequestParam(value = "phoneNumber", defaultValue = "null") String phoneNumber) {

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

    @PostMapping("/api/user/login")
    public ResponseEntity<?> userSignIn(@RequestParam(value = "username") String username,
                                        @RequestParam(value = "password") String password,
                                        HttpSession httpSession) {

        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        String passwordFromDatabase = userService.getPasswordByUsername(username);
        if (!passwordFromDatabase.equalsIgnoreCase(sha256hex)) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        } else if (userService.getRoleByUsername(username).equalsIgnoreCase("admin")) {
            httpSession.setAttribute("username", username);
            return new ResponseEntity<>("You have logged in as admin!", HttpStatus.OK);
        } else {
            httpSession.setAttribute("username", username);
            return new ResponseEntity<>("You have logged in successfully!", HttpStatus.OK);
        }
    }

    @PostMapping("/api/user/logout")
    public ResponseEntity<?> userSignOut(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("You are currently not logged in", HttpStatus.UNAUTHORIZED);
        } else {
            httpSession.removeAttribute("username");
            return new ResponseEntity<>("You have logged out successfully!", HttpStatus.OK);
        }
    }

    @GetMapping("/api/user/userProfile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable(value = "userId") int userId, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.UNAUTHORIZED);
        } else {
            String username = (String) httpSession.getAttribute("username");
            UserProfile userProfile = userService.getUserProfile(userId);
            if (userProfile == null) {
                return new ResponseEntity<>("User Profile not found!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/api/user/notification/new")
    public ResponseEntity<?> getNewNotifications(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            // Logic for JSON response of all the new notifications which are not read by the user
            // Also mark the new notifications as read
            return null;
        }
    }

    @GetMapping("/api/user/notification/all")
    public ResponseEntity<?> getAllNotifications(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            // Logic for JSON response of all the notifications of the user
            // Also mark the new notifications as read
            return null;
        }
    }
}
