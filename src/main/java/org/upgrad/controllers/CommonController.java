package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.upgrad.services.CategoryService;

@Controller
public class CommonController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/api/categories/all")
	public ResponseEntity<?> getAllCategories() {
		ResponseEntity<?> categoryList = new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);

		return categoryList;
	}

	@GetMapping("/api/questions/all")
	public ResponseEntity<?> getAllQuestions() {
		ResponseEntity<?> questionsList = new ResponseEntity<>("All Questions", HttpStatus.OK);

		return questionsList;
	}

}
