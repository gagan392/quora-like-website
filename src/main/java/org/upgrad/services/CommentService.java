package org.upgrad.services;

public interface CommentService {

	public void giveComment(String comment, Long answerId, Long userId);
}
