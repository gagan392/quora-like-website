package org.upgrad.repositories;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO comment(content, user_id, answer_id, date, modified_on) values(?1,?2,?3,?4,?5)")
	public void giveComment(String content, long userId, long answerId, Date date, Date modifiedOn);
}
