package org.upgrad.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
