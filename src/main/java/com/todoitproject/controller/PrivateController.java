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

import com.todoitproject.utils.AuthChecker;
import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.ILogService;
import com.todoitproject.service.IProjectService;

@CrossOrigin
@RestController
@RequestMapping(value="/api/private")
public class PrivateController {
	
	@Autowired IGlobalService iService;
	@Autowired ILogService iLogService;
	@Autowired IProjectService ipService;
	
	@Autowired
	private AuthChecker authChecker;
	
	@GetMapping(value = "/me")
	@ResponseBody
	public DtoUserLog findOneMe() {
		if (authChecker.isUser() == null) throw new com.todoitproject.exception.NotIdentifiedException();
		return iLogService.findOne(authChecker.isUser().getId());
		
	}
	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public DtoProject addProject(@RequestBody DtoProject dtoproject) {
		System.out.println("Salut MAria");
		System.out.println(dtoproject.getId_user());
		System.out.println(dtoproject.getDescription());
		System.out.println(dtoproject.getNom());
		return ipService.addProject(dtoproject);
	}
	
	
	@GetMapping(value="/MyProjects/{id_user}")
	@ResponseBody
	public List<DtoProject> listProject(@PathVariable long id_user) {
		return ipService.listProject(id_user);
	}
	
	@GetMapping(value="/OneProject/{id}")
	@ResponseBody
	public DtoProject oneProject(@PathVariable long id) {
		return ipService.oneProject(id);
	}
	
	@PostMapping(value = "/modifProjectNom")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public DtoProject saveNom(@RequestBody long id_user, DtoProject dtoproject) {
		return ipService.updateProjectName( id_user, dtoproject);
	}
	
	@PostMapping(value = "/modifProjectDescription")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public DtoProject saveDesc(@RequestBody long id_user, DtoProject dtoproject) {
		return ipService.updateProjectDescription( id_user, dtoproject);
	}

	// a corriger??
	
	@DeleteMapping(value = "/deleteMyProject")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteProject(@RequestBody long id) {
		  ipService.deleteProject(id);
		  return;	
	}

}
