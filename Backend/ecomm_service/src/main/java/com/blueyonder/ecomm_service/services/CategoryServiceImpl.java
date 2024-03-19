package com.blueyonder.ecomm_service.services;
import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;
import com.blueyonder.ecomm_service.exceptions.CategoryNotFoundException;
import com.blueyonder.ecomm_service.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		logger.info("All categories generated!!!");
		return categoryRepo.findAll();
	}

	@Override
	public Optional<Category> getCategoryByName(String catName)throws CategoryNotFoundException {
		// TODO Auto-generated method stub
		Optional<Category> c = categoryRepo.findByCategoryName(catName);
		if (c.isPresent())
		{
			logger.info("The category has been found!!");
			return c;
		}
		else {
			logger.error("The category with the name "+catName+" doesn't exist!!");
			return null;
			
		}
	}

	@Override
	public void deleteCategory(Integer catId) throws CategoryNotFoundException {
		// TODO Auto-generated method stub
		logger.info("The category deleted succesfully!!");
		categoryRepo.deleteById(catId);
		
	}

	@Override
	public Optional<Category> getCategoryById(Integer catId) throws CategoryNotFoundException {
		// TODO Auto-generated method stub
		Optional<Category> c = categoryRepo.findById(catId);
		if (c.isPresent())
		{
			logger.info("The category has been found!!");
			return c;
		}
		else {
			logger.error("The category with the name "+catId+" doesn't exist!!");
			return null;
			
		}
	}

	@Override
	public Category updateCategoryById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Category> cat = categoryRepo.findById(id);
		return categoryRepo.save(cat);
	}
	

}
