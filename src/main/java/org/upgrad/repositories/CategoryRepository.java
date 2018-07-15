package org.upgrad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

@Repository
public interface CategoryRepository {

	@Query(nativeQuery = true, value = "select * from category")
	public List<Category> getAllCategories();
}
