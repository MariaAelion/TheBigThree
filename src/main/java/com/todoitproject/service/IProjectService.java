package com.todoitproject.service;

import java.util.List;

import com.todoitproject.dto.DtoBoolean;
import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoProjectDescription;
import com.todoitproject.dto.DtoProjectName;
import com.todoitproject.dto.DtoRProject;

/**
*  @author TheBigThree
* @version 1.0.0
*
*/
public interface IProjectService {

    boolean addProject(long id_user, DtoProject dtoproject);

	List<DtoRProject> listProject(long id);

	//DtoProject oneProject(long id);

	DtoBoolean deleteProject(long id);

	boolean updateProjectName(long id, DtoProjectName dtoProjectname);

	boolean updateProjectDescription(long id, DtoProjectDescription dtoProjectdescription);

	




	

}
