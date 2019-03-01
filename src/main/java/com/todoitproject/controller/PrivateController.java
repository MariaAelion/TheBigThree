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
import com.todoitproject.exception.NotIdentifiedException;
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
		if (authChecker.isUser() == null) throw new NotIdentifiedException();
		return iLogService.findOne(authChecker.isUser().getId());
		
	}
	
	@DeleteMapping(value = "/me")
	@ResponseBody
	public DtoUserLog DeleteMe() {
		if (authChecker.isUser() == null) throw new NotIdentifiedException();
		return iLogService.DeleteOne(authChecker.isUser().getId());
		
	}
	
	
	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public DtoProject addProject(@RequestBody DtoProject dtoproject) {
		return ipService.addProject(dtoproject);
	}
	
	
	


}
