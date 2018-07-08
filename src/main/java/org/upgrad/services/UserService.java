package org.upgrad.services;

import org.upgrad.models.User;

import java.util.Date;

public interface UserService {

    void addUser(String username, String password, String email, String firstName, String lastName,
                 String aboutMe, Date dob, String contactNumber, String country);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
