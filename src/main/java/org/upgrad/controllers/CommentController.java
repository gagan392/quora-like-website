package org.upgrad.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.services.CommentService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/api/comment")
	public ResponseEntity<?> giveComment(@RequestParam String comment, @RequestParam long answerId,
			HttpSession httpSession) {

		if (httpSession.getAttribute("username") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			commentService.giveComment(comment, answerId, 0l);
		}

		return null;
	}
}
