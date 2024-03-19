package com.blueyonder.ecomm_service.services;

import java.util.List;
import java.util.Optional;

import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;
import com.blueyonder.ecomm_service.exceptions.CategoryNotFoundException;

public interface CategoryService {
	
	public Category addCategory(Category category);
	public List<Category> getAllCategories();
	public Optional<Category> getCategoryByName(String catName) throws CategoryNotFoundException;
	public void deleteCategory(Integer catId) throws CategoryNotFoundException;
	public Optional<Category> getCategoryById(Integer catId) throws CategoryNotFoundException;
	//public List<Product> getAllProductByCategory(Category category) throws ProductNotFoundException;
	public Category updateCategoryById(Integer id);
	


}
