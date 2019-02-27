package com.todoitproject.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoRCreateUser;

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

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	UserRepository userRepository;

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

		Optional<EUser> oeUser = userRepository.findUserById(id);

		if (oeUser.isPresent()) {
			return oeUser.get();

		} else {
			throw new com.todoitproject.exception.NotFoundException("Cet utilisateur n'existe pas");
		}
	}

	/**
	 * Création d'un nouveau projet
	 * 
	 * @param DtoCreateProject
	 * @return dtocreateproject
	 */
	public DtoProject addProject(DtoProject dtoproject) {

		// EUser eUser = getUserByLog(dtoproject.getId_user()); // recupere id user en
		// passant par methode car type
		// EUSer

		EProject project = new EProject();
		project.setNom(dtoproject.getNom());
		project.setDescription(dtoproject.getDescription());
		// project.seteUser(eUser);

		projectRepository.save(project);
		System.out.println("le projet " + project.getId() + " a bien été créé");

		return dtoproject;
	}

	
	
	// lister mes projets
	
	public List<DtoProjet> listProject
	
	
// supprimer un projet
	@Override
	public void deleteProject(long id) {
		Optional<EProject> oeProject = projectRepository.findById(id);

		if (oeProject.isPresent()) {
			projectRepository.delete(oeProject.get());
			;

		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");
		}
		return;

	}

	// modifier un projet
/*	@Override
public 	DtoProject updateProject(long id_user, DtoProject dtoproject) {
		 Optional<EProject> optProj = projectRepository.findById(id_user);
		
	
		if (optProj.isPresent()) {
			EProject projet = optProj.get();
		projet.setNom(dtoproject.getNom());
		projet.setDescription(dtoproject.getDescription());
		
		
		projectRepository.save(projet);
		System.out.println("le projet a bien été modifié");

		return dtoproject;
	} else {
		throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");
		
	}*/

	@Override
	public DtoProject updateProjectName(long id_user, DtoProject dtoproject) {
		Optional<EProject> optProj = projectRepository.findById(id_user);

		if (optProj.isPresent()) {
			EProject projet = optProj.get();
			projet.setNom(dtoproject.getNom());

			projectRepository.save(projet);
			System.out.println("le projet a bien été modifié");

			return dtoproject;
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");

		}

	}

	@Override
	public DtoProject updateProjectDescription(long id_user, DtoProject dtoproject) {
		Optional<EProject> optProj = projectRepository.findById(id_user);

		if (optProj.isPresent()) {
			EProject projet = optProj.get();
			projet.setDescription(dtoproject.getDescription());

			projectRepository.save(projet);
			System.out.println("le projet a bien été modifié");

			return dtoproject;
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");

		}

	}

}
