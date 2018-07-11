package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @DeleteMapping("/api/admin/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") int userId, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userService.getRoleByUsername((String) httpSession.getAttribute("username")).equalsIgnoreCase("admin")) {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User with userId " + userId + " deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You do not have rights to delete a user!", HttpStatus.UNAUTHORIZED);
        }
    }
}
