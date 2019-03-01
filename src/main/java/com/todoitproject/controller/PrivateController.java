package com.todoitproject.controller;



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
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.service.IEtaskService;
import com.todoitproject.service.IGlobalService;
import com.todoitproject.service.ILogService;
import com.todoitproject.service.IProjectService;

@CrossOrigin
@RestController
@RequestMapping(value="/api/private")
public class PrivateController {
	
	@Autowired IGlobalService iService;
	@Autowired ILogService iLogService;
	@Autowired IProjectService iPService;
	@Autowired IEtaskService iEtaskService;
	
	@Autowired
	private AuthChecker authChecker;
	
	@GetMapping(value = "/me")
	@ResponseBody
	public DtoUserLog findOneMe() {
		if (authChecker.isUser() == null) throw new NotIdentifiedException();
		return iLogService.findOne(authChecker.isUser().getId());
		
	}
	
	@DeleteMapping(value = "/me")
	@ResponseBody
	public DtoUserLog DeleteMe() {
		// TODO cascade des projets et taches.
		if (authChecker.isUser() == null) throw new NotIdentifiedException();
		return iLogService.DeleteOne(authChecker.isUser().getId());
		
	}
	
	@PutMapping(value = "/me/mail/{mail}")
	@ResponseBody
	public boolean changeMail(@PathVariable String mail) {
		if (authChecker.isUser() == null) throw new NotIdentifiedException();
		return iLogService.changeMail(authChecker.isUser().getId(), mail);
	}
	
	@PutMapping(value = "/me/password/{password}")
	@ResponseBody
	public boolean changePassword(@PathVariable String password) {
		if (authChecker.isUser() == null) throw new NotIdentifiedException();
		return iLogService.changePassword(authChecker.isUser().getId(), password);
	}
	
	
	@PostMapping(value = "/addProject")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public DtoProject addProject(@RequestBody DtoProject dtoproject) {
	
		return iPService.addProject(dtoproject);
	}
	
	

	@GetMapping(value="/MyProjects/{id_user}")
	@ResponseBody
	public List<DtoRProject> listProject(@PathVariable long id_user) {
		return iPService.listProject(id_user);
	}

		
	@GetMapping(value="/OneProject/{id}")
	@ResponseBody
	public DtoProject oneProject(@PathVariable long id) {
		return iPService.oneProject(id);
	}
	
	@PostMapping(value = "/modifProjectNom/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveNom(@PathVariable long id , @RequestBody DtoProjectName dtoprojectname) {
		return iPService.updateProjectName( id, dtoprojectname);
	}
	
	@PostMapping(value = "/modifProjectDescription/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public boolean saveDesc(@PathVariable long id , @RequestBody  DtoProjectDescription dtoprojectdescription) {
		return iPService.updateProjectDescription( id, dtoprojectdescription);
	}

	
	@DeleteMapping(value = "/deleteMyProject/{id}")
	@ResponseBody
	public DtoBoolean deleteProject(@PathVariable long id) {
		 return iPService.deleteProject(id);
		 
	}
	
	@GetMapping(value = "/deleteMyProject2/{id}")
	@ResponseBody
	public DtoBoolean deleteProject2(@PathVariable long id) {
		 return iPService.deleteProject(id);
		 
	}
	
	@DeleteMapping(value = "/delet/{id}")
	@ResponseBody
	public DtoBoolean delete(@PathVariable long id) {
		return iPService.deleteProject(id);
	}
	
	
	// Partie tâche
		/**
		 * 
		 * @param dtoTask
		 * @return Copie (DTO) de l'entité crée
		 */
		@PostMapping(value = "/addTask")
		@ResponseBody
		public DtoTask save(@RequestBody DtoTask dtoTask) {
			return iEtaskService.save(dtoTask);
		}

		/**
		 * 
		 * @param id
		 * @param dtoUpdateDate
		 * @return boolean, true si OK
		 */
		@PostMapping(value="/updateDateTask/{id}")
		@ResponseBody
		public boolean updateDate(@PathVariable long id, @RequestBody DtoUpdateDate dtoUpdateDate) {
			return iEtaskService.updateDate(id, dtoUpdateDate);
		}
		
		/**
		 * 
		 * @param id
		 * @param dtoUpdateEtat
		 * @return boolean, true si OK
		 */
		@PostMapping(value="/updateEtatTask/{id}")
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
		@PostMapping(value="/updateLabelTask/{id}")
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
		@PostMapping(value="/updateProjetTask/{id}")
		@ResponseBody
		public boolean updateLabel(@PathVariable long id, @RequestBody DtoUpdateProjet dtoUpdateProjet) {
			return iEtaskService.updateProjet(id, dtoUpdateProjet);
		}
		
		
		/**
		 * 
		 * @param id
		 */
		@DeleteMapping(value = "deleteTask/{id}")
		@ResponseStatus(code=HttpStatus.OK)
		public void deleteTask(@PathVariable long id) {
			iEtaskService.deleteById(id);
		}

}
