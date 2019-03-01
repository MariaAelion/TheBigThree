package com.todoitproject.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.utils.AuthChecker;
import com.todoitproject.dto.DtoBoolean;
import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoProjectDescription;
import com.todoitproject.dto.DtoProjectName;
import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.ILogService;
import com.todoitproject.service.IProjectService;

/**
* 
* @author TheBigThree
* @version 1.0.0
*
*/
@CrossOrigin
@RestController
@RequestMapping(value="/api/private")
public class PrivateController {
	
	@Autowired 
	IGlobalService iService;
	@Autowired 
	ILogService iLogService;
	@Autowired 
	IProjectService iPService;
	
	@Autowired
	private AuthChecker authChecker;
	
	@GetMapping(value = "/me")
	@ResponseBody
	public DtoUserLog findOneMe() {
		if (authChecker.isUser() == null) throw new com.todoitproject.exception.NotIdentifiedException();
		return iLogService.findOne(authChecker.isUser().getId());
		
	}
	
	/**
	 * 
	 * @param dtoproject
	 * @return dto de l'entité créée
	 */
	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public DtoProject addProject(@RequestBody DtoProject dtoproject) {
	
		return iPService.addProject(dtoproject);
	}
	
	/**
	 * 
	 * @param id_user
	 * @return liste des projets pour un utilisateur
	 */
	@GetMapping(value="/MyProjects")
	@ResponseBody
	public List<DtoRProject> listProject(){
		return iPService.listProject(authChecker.isUser().getId());
	}
	
		
	@GetMapping(value="/OneProject/{id}")
	@ResponseBody
	public DtoProject oneProject(@PathVariable long id) {
		return iPService.oneProject(id);
	}
	
	@PutMapping(value = "/modifProjectNom/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveNom(@PathVariable long id , @RequestBody DtoProjectName dtoprojectname) {
		return iPService.updateProjectName( id, dtoprojectname);
	}
	
	@PutMapping(value = "/modifProjectDescription/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveDesc(@PathVariable long id , @RequestBody  DtoProjectDescription dtoprojectdescription) {
		return iPService.updateProjectDescription( id, dtoprojectdescription);
	}


	
	@DeleteMapping(value = "/deleteMyProject/{id}")
	@ResponseBody
	public DtoBoolean deleteProject(@PathVariable long id) {
		 return iPService.deleteProject(id);
		 
	}
	
	@GetMapping(value = "/deleteMyProject2/{id}")
	@ResponseBody
	public DtoBoolean deleteProject2(@PathVariable long id) {
		 return iPService.deleteProject(id);
		 
	}
	
	
}
