package org.upgrad.services;

import org.upgrad.models.Comment;

public interface CommentService {

	public void giveComment(String comment, long answerId, long userId);
	public void editComment(String comment, long id);
	public Comment getCommentById(long id);
}
