package com.zee.zee5app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Register;

@RestController // 

@RequestMapping("/api/users")
public class UserController {
	
	//adding user info into table
	//info will be shared by the client in terms of json object
	//do we need to read that json object ? yes
	//where is this json object is available in request ? requestbody
	//do we need to read that requestbody content ? yes
	//do we need to transform json object to javaobject ? yes  @RequestBody
	//
	@PostMapping("/addUser")
	
	public String addUser(@RequestBody Register register) {
		return register.toString();
		
		
	}

}
