package com.todoitproject.service.impl;

import java.util.List;

import com.todoitproject.dto.DtoProject;

public interface IProjectService {

	void deleteProject(long id);

	DtoProject updateProjectName(long id_user, DtoProject dtoproject);

	DtoProject updateProjectDescription(long id_user, DtoProject dtoproject);

	DtoProject addProject(DtoProject dtoproject);

	List<DtoProject> listProject(long id_user);

	DtoProject oneProject(long id);

	

}
