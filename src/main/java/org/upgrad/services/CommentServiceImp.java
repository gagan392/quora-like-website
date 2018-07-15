package org.upgrad.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Comment;
import org.upgrad.repositories.CommentRepository;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public void giveComment(String comment, Long answerId, Long userId) {
		
		Comment entity = new Comment();
		entity.setAnswerId(answerId);
		entity.setContent(comment);
		entity.setUserId(userId);
		entity.setDate(new Date());
		entity.setModifiedOn(new Date());
		
		commentRepository.save(entity);
	}

}
