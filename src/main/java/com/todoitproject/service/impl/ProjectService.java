package com.todoitproject.service.impl;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todoitproject.dto.DtoBoolean;
import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoProjectDescription;
import com.todoitproject.dto.DtoProjectName;
import com.todoitproject.dto.DtoRProject;
import com.todoitproject.exception.NotFoundException;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.IProjectService;
/**
 * @name : pojectService
*  @author TheBigThree
* @version 1.0.0
*
*/
@Service
@Transactional

public class ProjectService implements IProjectService {

	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IGlobalService iGlobalService;

	/**
	 * Création d'un nouveau projet
	 * 
	 * @param DtoProject
	 * @return dtoproject
	 */
	@Override
	public DtoProject addProject(DtoProject dtoproject) {

		EProject eProject = new EProject();
		
		eProject.setNom(dtoproject.getNom());
		

		if (this.checkUserByLog(dtoproject.getId_user())) {
			eProject.seteUser(this.getUserByLog(dtoproject.getId_user()));
			projectRepository.save(eProject);
			return dtoproject;
		} else {
			throw new NotFoundException("Cet utilisateur n'existe pas");
		}
	}

	/**
	 * Lister les projets d'un utilisateur
	 * 
	 * @param l'id de l'utilisateur
	 * @return une liste de DtoRProject avec le nom et description des projets
	 */

	@Override
	public List<DtoRProject> listProject(long id) {
		List<EProject> projets = projectRepository.findByUser(id);

		return projets.stream().map(a -> new DtoRProject(a))
				.sorted((b1, b2) -> b1.getNom().compareTo(b2.getNom()))
				.collect(Collectors.toList());

	}

	/*
	 * choisir la bonne methode A NE PAS UTILISER
	 * 
	 * @Override public List<DtoProject> listProjectAll(long id) { List<EProject>
	 * projets = projectRepository.findAll(); Optional<EProject> optProject =
	 * projectRepository.findById(id);
	 * 
	 * return projets.stream().map(a -> new DtoProject(a))
	 * //.filter(b->b.getId_user() == (this.getUserByLog(id))) // probleme pas meme
	 * type .filter(b->b.setId_user(().getUserByLog(id)this.getUserByLog().get))
	 * .collect(Collectors.toList());
	 * 
	 * }
	 */

	/**
	 * Trouver un projet
	 * 
	 * @param l'id de l'utilisateur
	 * @return un projet avec DtoRProject avec son nom et sa description
	 */
	@Override
	public DtoProject oneProject(long id) {
		DtoProject proj = new DtoProject();
		Optional<EProject> optProject = projectRepository.findById(id);

		if (optProject.isPresent()) {

			proj.setNom(optProject.get().getNom());
			proj.setId_user(optProject.get().getId());
			return proj;
		} else {
			throw new NotFoundException("Ce projet n'existe pas");
		}
	}

	/**
	 * Supprimer un projet
	 * 
	 * @param l'id du projet
	 * @return true pour confirmer la suppression du projet
	 */
	@Override
	public DtoBoolean deleteProject(long id) {
		DtoBoolean dtoBoolean = new DtoBoolean();
		Optional<EProject> oeProject = projectRepository.findById(id);

		if (oeProject.isPresent()) {

			if (oeProject.get().getId() != 1) {

				projectRepository.delete(oeProject.get());
				// System.out.println("testdelete");
				dtoBoolean.setBool(true);

			} else {
				// System.out.println("testdeletefaux");
				dtoBoolean.setBool(false);

			}
		}

		else {
			dtoBoolean.setBool(false);

		}
		return dtoBoolean;
	}

	/**
	 * Modifier le nom d'un projet
	 * 
	 * @param l'id du projet et le DtoProjectName pour remplacer le champ nom dans  entité
	 * @return true si bien modifié
	 */
	@Override
	public boolean updateProjectName(long id, DtoProjectName dtoProjectname) {

		Optional<EProject> optProject = projectRepository.findById(id);

		if (optProject.isPresent()) {

			EProject eProj = new EProject();

			eProj = optProject.get();

			eProj.setNom(dtoProjectname.getNom());
			projectRepository.save(eProj);
			return true;

		} else {
			throw new NotFoundException("Ce projet n'existe pas");
		}
		
	}

	/**
	 * Modifier la description d'un projet
	 * 
	 * @param l'id du projet et le DtoProjectDescription pour remplacer le champ
	 *        description dans l'entité
	 * @return true
	 */

	@Override
	public boolean updateProjectDescription(long id, DtoProjectDescription dtoProjectdescription) {

		Optional<EProject> optProject = projectRepository.findById(id);

		if (optProject.isPresent()) {

			EProject eProj = new EProject();

			eProj = optProject.get();

			projectRepository.save(eProj);
			return true;

		} else {
			throw new NotFoundException("Ce projet n'existe pas");
		}

	
	}

	/**
	 * Vérifie qu'un utilisateur est bien connecté
	 * 
	 * @param id
	 * @return
	 */

	private boolean checkUserByLog(long id) {

		Optional<EUser> oeUser = userRepository.findUserById(id);

		if (oeUser.isPresent()) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * Récupère l'id de l'utilisateur connecté
	 * 
	 * @param id
	 * 
	 */
	private EUser getUserByLog(long id) {

		Optional<EUser> oeUser = userRepository.findById(id);
		if (oeUser.isPresent()) {
			return oeUser.get();
		} else {
			throw new NotFoundException("Cet utilisateur n'existe pas");
		}
	}

}
