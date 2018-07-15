package org.upgrad.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	
}
