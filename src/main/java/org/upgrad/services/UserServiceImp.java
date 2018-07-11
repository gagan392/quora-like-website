package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.UserProfile;
import org.upgrad.repositories.UserProfileRepository;
import org.upgrad.repositories.UserRepository;

import java.util.Date;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserServiceImp(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void addUser(String username, String password, String email, String firstName, String lastName,
                        String aboutMe, Date dob, String contactNumber, String country) {
        userRepository.addUser(username, password, email);
        User user = userRepository.findUserByUsername(username);
        userProfileRepository.addUserProfile(user.getId(), firstName, lastName, aboutMe, dob, contactNumber, country);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public String getPasswordByUsername(String username) {
        User user = findUserByUsername(username);
        return user.getPassword();
    }

    @Override
    public String getRoleByUsername(String username) {
        User user = findUserByUsername(username);
        return user.getRole();
    }

    @Override
    public UserProfile getUserProfile(int userId) {
        return userProfileRepository.findUserProfile(userId);
    }
}
