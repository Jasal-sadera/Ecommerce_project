package com.blueyonder.loginservice.controller;

import java.util.Map;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueyonder.loginservice.entities.UserEntity;
import com.blueyonder.loginservice.exceptions.PasswordMismatchException;
import com.blueyonder.loginservice.exceptions.UserNameNotFoundException;
import com.blueyonder.loginservice.service.UserService;
@RestController
@RequestMapping(value = "/loginservice/api/v1")
//@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping(value = "/registration")
	public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) throws PasswordMismatchException  {		
			UserEntity  user1 =   userService.createUser(user);
			return new ResponseEntity<>(user1,HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<UserEntity> userLogin(@RequestBody Map<String, String> map) throws UserNameNotFoundException,PasswordMismatchException  {
		String userName = map.get("username");
		String password = map.get("password1");
		UserEntity user = userService.validateUser(userName, password);
		return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
	
	}
	
}
	
