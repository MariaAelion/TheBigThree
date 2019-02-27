package com.todoitproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todoitproject.dto.DtoProject;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.repository.ProjectRepository;



@Service
@Transactional

public class ProjectService implements IProjectService{
	
	@Autowired
	ProjectRepository projectRepository;
	
	/**
	 * Création d'un nouveau projet
	 * 
	 * @param DtoCreateProject
	 * @return dtocreateproject
	 */
	
	@Override
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
	
	@Override
	public List<DtoProject> listProject(long id_user) {
		List<EProject> projects = projectRepository.findAll();
		return projects.stream().map(p-> new DtoProject(p));
				//.filter(predicate)
			//	.collect(Collector.toList()));
				
		
		
	/*	return projects.stream()
				.filter(m->m.getId_user().equals("id_user"))		
				.collect(Collectors.toList()));*/

		
	}
	
	// Trouver un projet
	
	@Override
	public DtoProject oneProject(long id) {
		DtoProject proj= new DtoProject();
		Optional<EProject> optProject= projectRepository.findById(id);
		
			if(optProject.isPresent()) {
				// EProject projet = optProject.get();
				proj.setNom(optProject.get().getNom());
				proj.setDescription(optProject.get().getDescription());
				proj.setId_user(optProject.get().getId());
				return proj;
			}else {
				throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");
			}
			
		
	}
	
	
// supprimer un projet
	
	@Override
	public void deleteProject(long id) {
		Optional<EProject> oeProject = projectRepository.findById(id);

		if (oeProject.isPresent()) {
			
			projectRepository.delete(oeProject.get());
			
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");
		}
		

	}

	
	
	// Modifier le nom d'un projet

	@Override
	public DtoProject updateProjectName(long id_user, DtoProject dtoproject) {
		Optional<EProject> optProj = projectRepository.findById(id_user);

		if (optProj.isPresent()) {
			EProject projet = optProj.get();
			projet.setNom(dtoproject.getNom());

			projectRepository.save(projet);
		//	System.out.println("le projet a bien été modifié");

			return dtoproject;
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");

		}

	}
// Modifier la description d'un projet
	
	@Override
	public DtoProject updateProjectDescription(long id_user, DtoProject dtoproject) {
		Optional<EProject> optProj = projectRepository.findById(id_user);

		if (optProj.isPresent()) {
			EProject projet = optProj.get();
			projet.setDescription(dtoproject.getDescription());

			projectRepository.save(projet);
		//	System.out.println("le projet a bien été modifié");

			return dtoproject;
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");

		}

	}
}
