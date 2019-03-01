package com.todoitproject.service;

import java.util.List;

import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoProjectDescription;
import com.todoitproject.dto.DtoProjectName;
import com.todoitproject.dto.DtoRProject;


public interface IProjectService {

	DtoProject addProject(DtoProject dtoproject);

	List<DtoRProject> listProject(long id);

	DtoProject oneProject(long id);

	boolean deleteProject(long id);

	boolean updateProjectName(long id, DtoProjectName dtoProjectname);

	boolean updateProjectDescription(long id, DtoProjectDescription dtoProjectdescription);

	




	

}
