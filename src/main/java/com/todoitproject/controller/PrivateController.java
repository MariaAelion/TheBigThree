package com.todoitproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.utils.AuthChecker;
import com.todoitproject.dto.DtoBoolean;
import com.todoitproject.dto.DtoProject;
import com.todoitproject.dto.DtoProjectDescription;
import com.todoitproject.dto.DtoProjectName;
import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoTask;

import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.exception.NotIdentifiedException;

import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.dto.ETask.DtoRTasks;

import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdatePriorite;
import com.todoitproject.dto.ETask.DtoUpdateProjet;

import com.todoitproject.service.IEtaskService;

import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.service.IEtaskService;

import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.ILogService;
import com.todoitproject.service.IProjectService;

/**
 * @author TheBigThree
 * @version 1.0.1
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/private")
public class PrivateController {

	@Autowired
	IGlobalService iService;
	@Autowired
	ILogService iLogService;
	@Autowired
	IProjectService iPService;
	@Autowired
	IEtaskService iEtaskService;

	@Autowired
	private AuthChecker authChecker;

	//// Partie utilisateur//////

	/**
	 * @description permet de verifier qu'un utilisateur est dans base de donnée
	 * @return un id de utilisateur
	 */
	@GetMapping(value = "/me")
	@ResponseBody
	public DtoUserLog findOneMe() {
		if (authChecker.isUser() == null)
			throw new NotIdentifiedException();
		return iLogService.findOne(authChecker.isUser().getId());

	}

	/**
	 * @description Supprime un utilisateur
	 */
	@DeleteMapping(value = "/me")
	@ResponseBody
	public DtoUserLog DeleteMe() {
		// TODO cascade des projets et taches.
		if (authChecker.isUser() == null)
			throw new NotIdentifiedException();
		return iLogService.DeleteOne(authChecker.isUser().getId());

	}

	/**
	 * @description changer le mail d'un utilisateur
	 * @param mail
	 * @return un mail mis à jour pour un utilisateur
	 */
	@PutMapping(value = "/me/mail/{mail}")
	@ResponseBody
	public boolean changeMail(@PathVariable String mail) {
		if (authChecker.isUser() == null)
			throw new NotIdentifiedException();
		return iLogService.changeMail(authChecker.isUser().getId(), mail);
	}

	/**
	 * @description changer le mot de passe d'un utilisateur
	 * @param password
	 * @return un password modifié
	 */
	@PutMapping(value = "/me/password/{password}")
	@ResponseBody
	public boolean changePassword(@PathVariable String password) {
		if (authChecker.isUser() == null)
			throw new NotIdentifiedException();
		return iLogService.changePassword(authChecker.isUser().getId(), password);
	}

	///////////// Partie projet//////////////////////////
	/**
	 * @description ajoute un projet
	 * @param dtoproject
	 * @return un projet ajouté dans la base de donnée
	 */
	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean addProject(@RequestBody DtoProject dtoproject) {

		return iPService.addProject(authChecker.isUser().getId(), dtoproject);
	}

	/**
	 * @description lister les projet d'un utilisateur
	 * @return une liste de projets
	 */
	@GetMapping(value = "/MyProjects")
	@ResponseBody
	public List<DtoRProject> listProject() {
		return iPService.listProject(authChecker.isUser().getId());
	}
	
	
	/**
	 * @description modifier le nom d'un projet
	 * @param id
	 * @param dtoprojectname
	 * @return un nouveau nom de projet si true =ok
	 */

	@PutMapping(value = "/modifProjectNom/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveNom(@PathVariable long id, @RequestBody DtoProjectName dtoprojectname) {
		return iPService.updateProjectName(id, dtoprojectname);
	}
	
	/**
	 * @description modifier la description d'un projet
	 * @param id
	 * @param dtoprojectdescription
	 * @return un nouveau description de projet si true =ok
	 */

	@PutMapping(value = "/modifProjectDescription/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveDesc(@PathVariable long id, @RequestBody DtoProjectDescription dtoprojectdescription) {
		return iPService.updateProjectDescription(id, dtoprojectdescription);
	}
	
	/**
	 * @description supprimer un projet
	 * @param id
	 * @return supprime un fichier
	 */

    @DeleteMapping(value = "/deleteMyProject/{id}")
	@ResponseBody
	public DtoBoolean deleteProject(@PathVariable long id) {
		return iPService.deleteProject(id);
	}


	////////////////// Partie tâche//////////////////////////
	/**
	 * 
	 * @param dtoTask
	 * @return Copie (DTO) de l'entité crée
	 */
	@PostMapping(value = "/addTask")
	@ResponseBody
	public DtoTask save(@RequestBody DtoTask dtoTask) {
		if (authChecker.isUser() == null)
			throw new NotIdentifiedException();
		return iEtaskService.save(authChecker.isUser().getId(), dtoTask);
	}

	/**
	 * 
	 * @param id
	 * @param dtoUpdateDate
	 * @return boolean, true si OK
	 */
	@PostMapping(value = "/updateDateTask/{id}")
	@ResponseBody
	public boolean updateDate(@PathVariable long id, @RequestBody DtoUpdateDate dtoUpdateDate) {
		return iEtaskService.updateDate(id, dtoUpdateDate);
	}
	/**
	 * 
	 * @param id
	 * @param dtoUpdatepriorite
	 * @return boolean, true si OK
	 */

	@PutMapping(value = "/updatePrioriteTask/{id}")
	@ResponseBody
	public boolean updatePriorite(@PathVariable long id, @RequestBody DtoUpdatePriorite dtoUpdatePriorite) {
		return iEtaskService.updatePriorite(id, dtoUpdatePriorite);
	}

	/**
	 * 
	 * @param id
	 * @param dtoUpdateEtat
	 * @return boolean, true si OK
	 */
	@PostMapping(value = "/updateEtatTask/{id}")
	@ResponseBody
	public boolean updateEtat(@PathVariable long id, @RequestBody DtoUpdateEtat dtoUpdateEtat) {
		return iEtaskService.updateEtat(id, dtoUpdateEtat);
	}

	/**
	 * 
	 * @param id
	 * @param dtoUpdateLabel
	 * @return boolean, true si OK
	 */
	@PostMapping(value = "/updateLabelTask/{id}")
	@ResponseBody
	public boolean updateLabel(@PathVariable long id, @RequestBody DtoUpdateLabel dtoUpdateLabel) {
		return iEtaskService.updateLabel(id, dtoUpdateLabel);
	}

	/**
	 * 
	 * @param id
	 * @param dtoUpdateProjet
	 * @return boolean, true si OK
	 */
	@PostMapping(value = "/updateProjetTask/{id}")
	@ResponseBody
	public boolean updateProject(@PathVariable long id, @RequestBody DtoUpdateProjet dtoUpdateProjet) {
		return iEtaskService.updateProjet(id, dtoUpdateProjet);
	}
	
	/**
	 * @description afficher la liste des taches par utilisateur
	 * @return une liste de dtoRtasks
	 */

	@GetMapping(value = "/getAllTasks")
	@ResponseBody
	public List<DtoRTasks> getAllTasks() {
		List<DtoRProject> list = iPService.listProject(authChecker.isUser().getId());
		return iEtaskService.getAllTasks(list);
	}

	// test

	@GetMapping(value = "/getAllTasksForADay/{dateLimite}")
	@ResponseBody
	public List<DtoRTasks> getAllTasksForADay(@PathVariable LocalDate dateLimite) {

		List<DtoRProject> list = iPService.listProject(authChecker.isUser().getId());

		return iEtaskService.getAllTasksForADay(list, dateLimite);
	}

	/**
	 * @description supprime une tache
	 * @param id
	 */
	@DeleteMapping(value = "deleteTask/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteTask(@PathVariable long id) {
		iEtaskService.deleteById(id);
	}

	/*
	 * @GetMapping(value="/OneProject/{id}")
	 * 
	 * @ResponseBody public DtoProject oneProject(@PathVariable long id) { return
	 * iPService.oneProject(id); }
	 */

}
