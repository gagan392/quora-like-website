package org.upgrad.services;

import java.util.List;

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
	public List<Category> getAllCategories() {
		return categoryRepository.getAllCategories();
	}

}
