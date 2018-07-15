package org.upgrad.services;


import org.upgrad.models.Question;
import org.upgrad.models.User;

import java.util.List;

public interface QuestionService {

    Question getQuestionById(int id);
    List<Question> getAllQuestionsByUser(int userId);
    void deleteQuestion(int id);
}
