package com.todoitproject.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoCreateProject;
import com.todoitproject.dto.DtoTask;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.TaskRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.IGlobalService;

@Service
@Transactional
public class GlobalService implements IGlobalService {
	
	@Autowired ProjectRepository projectRepository;
	@Autowired TaskRepository taskRepository;
	@Autowired UserRepository userRepository;
	
	
	
	
	@Override
	public DtoTask save(DtoTask dtoTask) {
		
		ETask eTask = new ETask();
		eTask.setDateCrea(dtoTask.getDateCrea());
		eTask.setDateLimite(dtoTask.getDateLimite());
		eTask.setEtat(dtoTask.isEtat());
		eTask.setLabel(dtoTask.getLabel());
		eTask.setPriorite(dtoTask.getPriorite());
		
		taskRepository.save(eTask);		
		return dtoTask;
	}
	
	

	private EUser getUserByLog(long id) {
		ETask eTask = new ETask();
		Optional<EUser> oeUser = userRepository.findUserById(id);
		
		if (oeUser.isPresent()) {
			return oeUser.get();
			
		} else {
			throw new com.todoitproject.exception.NotFoundException ("Cet utilisateur n'existe pas");
		}
		

	}
	
	@Override
	
	/**
	 * Création d'un nouveau projet
	 * @param DtoCreateProject
	 * 
	 */
	public DtoCreateProject addProject(DtoCreateProject dtocreateproject) {
		EProject project = new EProject();
		project.setNom(dtocreateproject.getNom());
		project.setDescription(dtocreateproject.getDescription());
				
		 projectRepository.save(project);
		 System.out.println("le projet " +  project.getId() + " a bien été créé");
		 
		 return dtocreateproject;
		
	}

}
