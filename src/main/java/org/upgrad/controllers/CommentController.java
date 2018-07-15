package org.upgrad.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.upgrad.models.Comment;
import org.upgrad.models.User;
import org.upgrad.services.CommentService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.UserService;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notificationService;

	@PostMapping("/api/comment")
	public ResponseEntity<?> giveComment(@RequestParam String content, @RequestParam long answerId,
			HttpSession httpSession) {

		if (httpSession.getAttribute("currUser") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			User currentUser = userService.findUserByUsername((String) httpSession.getAttribute("username"));
			commentService.giveComment(content, answerId, currentUser.getId());
			return new ResponseEntity<>("AnswerId " + answerId + " commented successfully.", HttpStatus.OK);
		}

	}

	@PutMapping("/api/comment/{commentId}")
	public ResponseEntity<?> editComment(@PathVariable(name = "commentId") int commentId, @RequestParam String content,
			HttpSession httpSession) {

		if (httpSession.getAttribute("username") == null) {
			return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
		} else {
			User currentUser = userService.findUserByUsername((String) httpSession.getAttribute("username"));
			Comment comment = commentService.getCommentById(commentId);
			if (currentUser.getRole().equals("admin") || currentUser.getId() == comment.getUserId()) {
				commentService.editComment(content, commentId);
				return new ResponseEntity<>("Comment with commentId " + commentId + " edited successfully.",
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>("You do not have rights to edit this comment.", HttpStatus.UNAUTHORIZED);

			}
		}

	}
}
