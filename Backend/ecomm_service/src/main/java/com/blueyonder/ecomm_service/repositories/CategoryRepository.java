package com.blueyonder.ecomm_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
	//public List<Category> findAllByCategory(Category category);
	public Optional<Category> findByCategoryName(String catName);
	@Query(value="delete from Category where id = ?1",nativeQuery=true)
	public void deleteCategory(Integer id);
	public Category save(Optional<Category> cat);
	

}
