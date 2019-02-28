package com.todoitproject.service.impl;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todoitproject.dto.DtoProject;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.IProjectService;



@Service
@Transactional

public class ProjectService implements IProjectService{
	
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IGlobalService iGlobalService;
	
	
	/**
	 * Création d'un nouveau projet
	 * @param DtoProject
	 * @return dtoproject
	 */
	@Override
	public DtoProject addProject(DtoProject dtoproject) {

		
		EProject eProject = new EProject();
		eProject.setNom(dtoproject.getNom());
		eProject.setDescription(dtoproject.getDescription());
			
		eProject.seteUser(this.getUserByLog(1));

		projectRepository.save(eProject);
	//	System.out.println("le projet " + project.getId() + " a bien été créé");

		return dtoproject;
	}

		
	/**
	 * Lister les projets d'un utilisateur 
	 * @param 
	 * @return
	 */
	
	@Override
	public List<DtoProject> listProject(long id_user) {
		List<EProject> projets = projectRepository.findByUser(id_user);
		
			 return projets.stream()
					  .map(a -> new DtoProject(a))
					 .collect(Collectors.toList());
		}
	
	/**
	 * Trouver un projet 
	 * @param 
	 * @return
	 */	
	@Override
	public DtoProject oneProject(long id) {
		DtoProject proj= new DtoProject();
		Optional<EProject> optProject= projectRepository.findById(id);
		
			if(optProject.isPresent()) {
		
				proj.setNom(optProject.get().getNom());
				proj.setDescription(optProject.get().getDescription());
				proj.setId_user(optProject.get().getId());
				return proj;
			}else {
				throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");
			}
			}
		
	/**
	 * Supprimer un projet 
	 * @param 
	 * @return
	 */
	@Override
	public void deleteProject(long id) {
		Optional<EProject> oeProject = projectRepository.findById(id);

		if (oeProject.isPresent()) {
			
			projectRepository.delete(oeProject.get());
			
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");
		}
		

	}

	/**
	 * Modifier le nom d'un projet 
	 * @param 
	 * @return
	 */
	@Override
	public DtoProject updateProjectName(long id_user, DtoProject dtoproject) {
		Optional<EProject> optProj = projectRepository.findById(id_user);

		if (optProj.isPresent()) {
			EProject projet = optProj.get();
			projet.setNom(dtoproject.getNom());

			projectRepository.save(projet);

			return dtoproject;
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");

		}

	}
	
	/**
	 * Modifier la description d'un projet 
	 * @param 
	 * @return
	 */
	@Override
	public DtoProject updateProjectDescription(long id_user, DtoProject dtoproject) {
		Optional<EProject> optProj = projectRepository.findById(id_user);

		if (optProj.isPresent()) {
			EProject projet = optProj.get();
			projet.setDescription(dtoproject.getDescription());

			projectRepository.save(projet);
	
			return dtoproject;
		} else {
			throw new com.todoitproject.exception.NotFoundException("Ce projet n'existe pas");

		}

	}


	@Override
	public List<DtoProject> listProject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	private boolean checkUserByLog(long id) {
		System.out.println("L'connard31");
		Optional<EUser> oeUser = userRepository.findUserById(id);
		System.out.println("L'connard32");
		if (oeUser.isPresent()) {
			System.out.println("L'connard est present");
			return true;
		}
		else {
			System.out.println("L'connard est pas present");
			return false;}
		
	}
	
	
	private EUser getUserByLog(long id) {

		Optional<EUser> oeUser = userRepository.findById(id);
		if (oeUser.isPresent()) {
			return oeUser.get();
		} else {
			throw new com.todoitproject.exception.NotFoundException("Cet utilisateur n'existe pas");
		}
	}




	
}
