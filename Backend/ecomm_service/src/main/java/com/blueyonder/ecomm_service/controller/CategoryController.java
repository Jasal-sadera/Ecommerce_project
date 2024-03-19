package com.blueyonder.ecomm_service.controller;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;
import com.blueyonder.ecomm_service.exceptions.CategoryNotFoundException;
import com.blueyonder.ecomm_service.exceptions.ProductNotFoundException;
import com.blueyonder.ecomm_service.services.CategoryService;
import com.blueyonder.ecomm_service.services.ProductService;

@RestController
//@CrossOrigin("*")
@RequestMapping("/category")

public class CategoryController {
	

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/addcategory")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		Category cat = categoryService.addCategory(category);
		return new ResponseEntity<Category>(cat,HttpStatus.CREATED);
		
	}
	
//	@GetMapping(value="/productsbycat")
//	public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam("category") Category category){
//		List<Product> products = null;
//		try {
//			products = categoryService.getAllProductByCategory(category);
//		} catch (ProductNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(!products.isEmpty())
//		{
//			return new ResponseEntity<List<Product>>(
//					products,
//					HttpStatus.OK);
//		}
//		return new ResponseEntity<List<Product>>(
//				HttpStatus.NOT_FOUND);
//		
//	}
	
	@GetMapping (value = "/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> cats =  categoryService.getAllCategories();
        if(!cats.isEmpty()) {
        	return new ResponseEntity<List<Category>>(
        			cats,
        			HttpStatus.OK);
        }
        return new ResponseEntity<List<Category>>(
        		HttpStatus.NOT_FOUND);       
    }
	
	@GetMapping(value = "/categorybyid")
	public Optional<Category> getCategoryById(@RequestParam("id") Integer id){
		
		Optional<Category> cat = null ;
		try {
			cat = categoryService.getCategoryById(id);
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cat;
		
	}
	
	@GetMapping(value="/categorybyname")
	public Category getCategorybyName(@RequestParam("name") String catName){
		// = java.util.Optional.empty() ;
		try {
			Optional<Category> cat = categoryService.getCategoryByName(catName);
			if(cat.isPresent()) {
				return cat.get();
			}
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@PostMapping("/deletecategory") 
	public ResponseEntity<String> deleteCategory(@RequestParam("id") Integer catId) throws CategoryNotFoundException{
		if(categoryService.getCategoryById(catId)!=null) {
			categoryService.deleteCategory(catId);
			return ResponseEntity.ok("Delete success");
		}
		else{
			// TODO Auto-generated catch block
			throw new CategoryNotFoundException();
			
		}
		
	}
	
	@PostMapping("/updatecategory")
	public ResponseEntity<Category> updateCategoryById(
	        @RequestParam(name = "id", required = false) Integer id,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestBody Category updateCategory) {

	    try {
	        Optional<Category> cat;
	        if (id != null) {
	            cat = categoryService.getCategoryById(id);
	        } else if (name != null) {
	            cat = categoryService.getCategoryByName(name);
	        } else {
	            throw new IllegalArgumentException("Either 'id' or 'name' must be provided.");
	        }

	        if (cat.isPresent()) {
	            Category existingCategory = cat.get();
	            BeanUtils.copyProperties(updateCategory, existingCategory, getNullPropertyNames(updateCategory));
	            categoryService.addCategory(existingCategory);
	            return new ResponseEntity<>(existingCategory, HttpStatus.OK);
	        } else {
	            throw new CategoryNotFoundException("Category not found");
	        }
	    } catch (CategoryNotFoundException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	private String[] getNullPropertyNames(Category source) {
        final BeanWrapperImpl wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
	
	

}
