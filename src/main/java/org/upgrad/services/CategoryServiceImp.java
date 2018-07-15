package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	private CategoryServiceImp(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}
