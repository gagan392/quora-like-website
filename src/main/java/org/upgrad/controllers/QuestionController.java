package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.upgrad.models.Question;
import org.upgrad.models.User;
import org.upgrad.services.QuestionService;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/question/all")
    public ResponseEntity<?> getAllQuestionsByUser(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        User current = userService.findUserByUsername((String) httpSession.getAttribute("username"));
        return new ResponseEntity<>(questionService.getAllQuestionsByUser((int) current.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/api/question/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable(name = "questionId") int questionId, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        Question question = questionService.getQuestionById(questionId);
        if(question == null) {
            return new ResponseEntity<>("Question with questionId " + questionId + " not found.", HttpStatus.NOT_FOUND);
        }

        User currentUser = userService.findUserByUsername((String) httpSession.getAttribute("username"));
        if (!currentUser.getRole().equalsIgnoreCase("admin")
                && !question.getUser().getUserName().equalsIgnoreCase(currentUser.getUserName())) {
            return new ResponseEntity<>("You do not have rights to delete this question!", HttpStatus.FORBIDDEN);
        }

        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>("Question with questionId " + questionId + " deleted successfully.", HttpStatus.OK);
    }

}
