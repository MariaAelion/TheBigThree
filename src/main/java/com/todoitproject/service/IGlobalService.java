package com.todoitproject.service;


import com.todoitproject.dto.DtoCreateProject;
import com.todoitproject.dto.DtoTask;


public interface IGlobalService {
	
	DtoTask save(DtoTask dtoTask);
	DtoCreateProject addProject(DtoCreateProject dtocreateproject) ;
}
