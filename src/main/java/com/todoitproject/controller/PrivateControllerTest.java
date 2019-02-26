package com.todoitproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.dto.DtoCreateProject;
import com.todoitproject.dto.DtoTask;
import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.impl.GlobalService;

@CrossOrigin
@RestController
@RequestMapping(value="/private/test")
public class PrivateControllerTest {
	
	@Autowired IGlobalService iService;
	@Autowired GlobalService gService;
	
	
	@PostMapping(value="/addTask")
	@ResponseBody
	public DtoTask save(@RequestBody DtoTask dtoTask) {
		return iService.save(dtoTask);
	}
	
	@PostMapping(value="/addProject")
	@ResponseBody
	public DtoCreateProject addProject(@RequestBody DtoCreateProject dtocreateproject) {
		return gService.addProject(dtocreateproject);
	}
}
