package com.todoitproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.service.IEtaskService;



@CrossOrigin
@RestController
@RequestMapping(value="/test")
public class PrivateControllerTest {
	
	@Autowired IEtaskService iEtaskService;
	
	
	// Partie tâche
	/**
	 * 
	 * @param dtoTask
	 * @return Copie (DTO) de l'entité crée
	 */
	@PostMapping(value="/addTask")
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
	public void delete(@PathVariable long id) {
		iEtaskService.deleteById(id);
	}

}
