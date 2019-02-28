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

import com.todoitproject.dto.DtoTask;

import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.IProjectService;


@CrossOrigin
@RestController

@RequestMapping(value="/api/test")

public class PrivateControllerTest {

	@Autowired
	IGlobalService iService;
	IProjectService pService;

	@PostMapping(value = "/addTask")
	@ResponseBody
	public DtoTask save(@RequestBody DtoTask dtoTask) {
		return iService.save(dtoTask);
	}

	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public DtoProject addProject(@RequestBody DtoProject dtoproject) {
		return pService.addProject(dtoproject);
	}
	
	
	@GetMapping(value="/MyProjects")
	@ResponseBody
	public List<DtoProject> listProject() {
		return pService.listProject();
	}
	
	
	
	@GetMapping(value="/MyProjects/{id_user}")
	@ResponseBody
	public List<DtoProject> listProject(@PathVariable long id_user) {
		return pService.listProject(id_user);
	}
	
	@GetMapping(value="/OneProject/{id}")
	@ResponseBody
	public DtoProject oneProject(@PathVariable long id) {
		return pService.oneProject(id);
	}
	
	@PostMapping(value = "/modifProjectNom")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public DtoProject saveNom(@RequestBody long id_user, DtoProject dtoproject) {
		return pService.updateProjectName( id_user, dtoproject);
	}
	
	@PostMapping(value = "/modifProjectDescription")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public DtoProject saveDesc(@RequestBody long id_user, DtoProject dtoproject) {
		return pService.updateProjectDescription( id_user, dtoproject);
	}

	// a corriger??
	
	@DeleteMapping(value = "/deleteMyProject")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteProject(@RequestBody long id) {
		  pService.deleteProject(id);
		  return;	
	}

}
