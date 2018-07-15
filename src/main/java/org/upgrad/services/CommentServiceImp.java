package org.upgrad.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.repositories.CommentRepository;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public void giveComment(String comment, Long answerId, Long userId) {
		commentRepository.giveComment(comment, userId, answerId, new Date(), new Date());
	}

}
