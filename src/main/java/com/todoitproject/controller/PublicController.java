package com.todoitproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;
import com.todoitproject.service.ILogService;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
@CrossOrigin
@RestController
@RequestMapping(value="/api/public")
public class PublicController {
	
	@Autowired ILogService iLogService;
	
	
	@PostMapping(value="/create")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public DtoRCreateUser createUser(@RequestBody DtoCreateUser  dtoCreateUser) {
		
		return  iLogService.createUser(dtoCreateUser);
	}

}
