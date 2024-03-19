package com.blueyonder.ecomm_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleCategoryNotFoundException(){
		return new ResponseEntity<>("Product doesn't exist",HttpStatus.NOT_FOUND);
	}
//	@ExceptionHandler(ProductNotFoundException.class)
//	public ResponseEntity<String> handleProductNotFoundException(){
//		return new ResponseEntity<>("Product doesnt exist",HttpStatus.NOT_FOUND);
//	}
}