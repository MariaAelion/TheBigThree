package com.todoitproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.dto.DtoTask;
import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.service.IGlobalService;

@CrossOrigin
@RestController
@RequestMapping(value="/test")
public class PrivateControllerTest {
	
	@Autowired IGlobalService iService;
	
	
	@PostMapping(value="/addTask")
	@ResponseBody
	public DtoTask save(@RequestBody DtoTask dtoTask) {
		return iService.save(dtoTask);
	}

}
