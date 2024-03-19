package com.blueyonder.ecomm_service.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blueyonder.ecomm_service.entities.Category;
import com.blueyonder.ecomm_service.entities.Product;
public interface ProductRepository extends JpaRepository<Product,Integer> {
	public List<Product> findAllByCategory(Category category);
	public Optional<Product> findAllByProductName(String name);
	@Query(value="delete from Product where id = ?1",nativeQuery=true)
	public void deleteProduct(Integer id);
	public Product save(Optional<Product> prod);

}
