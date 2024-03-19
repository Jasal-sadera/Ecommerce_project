package com.blueyonder.ecomm_service.controller;
import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.blueyonder.ecomm_service.services.ProductService;


@RestController
//@CrossOrigin("*")
@RequestMapping("/api/v1/product")
public class ProductController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ProductService productService;
	
	//http://localhost:8080/api/vi/product/addproduct?categoryId=1
	
	@PostMapping("/addproduct")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		Product prod = productService.addProduct(product);
		return new ResponseEntity<Product>(prod,HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/productsbycat")
	public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam("category") Category category){
		List<Product> products = null;
		try {
			products = productService.getAllProductByCategory(category);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!products.isEmpty())
		{
			return new ResponseEntity<List<Product>>(
					products,
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(
				HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping (value = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products =  productService.getAllProducts();
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<Product>>(
        			products,
        			HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(
        		HttpStatus.NOT_FOUND);       
    }
	
	@GetMapping(value = "/productbyid")
	public Product getProductById(@RequestParam("id") Integer id){
		
		Optional<Product> product; //= java.util.Optional.empty();
		try {
			product = productService.getProductById(id);
			if(product.isPresent()) {
				return product.get();
			}
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping(value="/productbyname")
	public ResponseEntity<Optional<Product>> getAllProductsbyName(@RequestParam("name") String name){
		Optional<Product> products = productService.getAllProductByName(name);
		if(products != null) {
			return new ResponseEntity<Optional<Product>>(
					products,
					HttpStatus.OK);
		}
		return new ResponseEntity<Optional<Product>>(
				products,
				HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/deleteproduct") 
	public ResponseEntity<String> deleteProduct(@RequestParam("id") Integer prodId) throws ProductNotFoundException{
		if(productService.getProductById(prodId)!=null) {
			productService.deleteProduct(prodId);
		}
		else{
			// TODO Auto-generated catch block
			throw new ProductNotFoundException();
		}
		return null;
		
	}
	
	@PostMapping("/updateproduct")
	public ResponseEntity<Product> updateProductById(
	        @RequestParam(name = "id", required = false) Integer id,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestBody Product updateProduct) {

	    try {
	        Optional<Product> prod;
	        if (id != null) {
	            prod = productService.getProductById(id);
	        } else if (name != null) {
	            prod = productService.getAllProductByName(name);
	        } else {
	            throw new IllegalArgumentException("Either 'id' or 'name' must be provided.");
	        }

	        if (prod.isPresent()) {
	            Product existingProduct = prod.get();
	            Product newprod = copyProperties(existingProduct,updateProduct);
	            productService.addProduct(newprod);
	            return new ResponseEntity<>(newprod , HttpStatus.OK);
	        } else {
	            throw new ProductNotFoundException("Product not found");
	        }
	    } catch (ProductNotFoundException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	private String[] getNullPropertyNames(Product updateProduct) {
        final BeanWrapperImpl wrappedSource = new BeanWrapperImpl(updateProduct);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
	private  Product copyProperties(Product existingProduct, Product updateProduct) {
	    if(updateProduct.getProductName() != null) {
	        existingProduct.setProductName(updateProduct.getProductName());
	    }
	    else if(updateProduct.getProductName() != null) {
	        existingProduct.setProductName(updateProduct.getProductName());
	    }
	    else if(updateProduct.getPrice() != null) {
	        existingProduct.setPrice(updateProduct.getPrice());
	    }
	    else if(updateProduct.getDes() != null) {
	        existingProduct.setDes(updateProduct.getDes());
	    }
	    
	    // Repeat the above if condition for all properties of the Product class
	    // For example, if Product has a property called 'description', you would do:
	    // if(updateProduct.getDescription() != null) {
	    //     existingProduct.setDescription(updateProduct.getDescription());
	    // }
	    return existingProduct;
	}
	

}
