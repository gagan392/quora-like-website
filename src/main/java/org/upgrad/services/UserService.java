package org.upgrad.services;

import org.upgrad.models.User;

public interface UserService {

    void addUser(String username, String password, String email);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
