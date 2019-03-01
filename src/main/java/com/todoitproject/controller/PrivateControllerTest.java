package com.todoitproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoProjectDescription;
import com.todoitproject.dto.DtoProjectName;
import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoTask;

import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.IProjectService;

/**
 * @
 * @author TheBigThree
 *@version 1.0.0
 */

@CrossOrigin
@RestController

@RequestMapping(value="/api/test")

public class PrivateControllerTest {

	@Autowired
	IGlobalService iService;
	@Autowired
	IProjectService iPService;
	
	

	@PostMapping(value = "/addTask")
	@ResponseBody
	public DtoTask save(@RequestBody DtoTask dtoTask) {
		return iService.save(dtoTask);
	}

	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public DtoProject addProject(@RequestBody DtoProject dtoproject) {
	
		return iPService.addProject(dtoproject);
	}
	
	
	@GetMapping(value="/MyProjects/{id_user}")
	@ResponseBody
	public List<DtoRProject> listProject(@PathVariable long id_user) {
		return iPService.listProject(id_user);
	}
	
		
	@GetMapping(value="/OneProject/{id}")
	@ResponseBody
	public DtoProject oneProject(@PathVariable long id) {
		return iPService.oneProject(id);
	}
	
	@PostMapping(value = "/modifProjectNom/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveNom(@PathVariable long id , @RequestBody DtoProjectName dtoprojectname) {
		return iPService.updateProjectName( id, dtoprojectname);
	}
	
	@PostMapping(value = "/modifProjectDescription/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveDesc(@PathVariable long id , @RequestBody  DtoProjectDescription dtoprojectdescription) {
		return iPService.updateProjectDescription( id, dtoprojectdescription);
	}


	
	@DeleteMapping(value = "/deleteMyProject/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean deleteProject(@PathVariable long id) {
		 return iPService.deleteProject(id);
		 
	}

}
