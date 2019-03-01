package com.todoitproject.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.exception.BeforeNowException;
import com.todoitproject.exception.NotFoundException;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.TaskRepository;
import com.todoitproject.service.IEtaskService;

@Service
@Transactional
public class EtaskService implements IEtaskService{
	
	
	@Autowired TaskRepository taskRepository;
	@Autowired ProjectRepository projectRepository;
	
	
		@Override
		public DtoTask save(DtoTask dtoTask) {
			
			
			
			ETask eTask = new ETask();
			
			if (dtoTask.getDateLimite().isAfter(LocalDate.now())) {
				
				eTask.setDateLimite(dtoTask.getDateLimite());
			
			
			
				eTask.setDateCrea(LocalDate.now());
			
				dtoTask.setDateCrea(eTask.getDateCrea());
		
				eTask.setEtat(false);
				
				eTask.setLabel(dtoTask.getLabel());
				
				eTask.setPriorite(dtoTask.getPriorite());
			
				if (dtoTask.geteProject() != null) {
					eTask.seteProject(dtoTask.geteProject());
				}
				
				if (dtoTask.geteProject() == null) {
					List<EProject> list = projectRepository.findAll();
					EProject eProject = list.get(0);
					eTask.seteProject(eProject);
					dtoTask.seteProject(eTask.geteProject());
				} 
					
			
				taskRepository.save(eTask);
			
			}
			
			else {
				
					throw new BeforeNowException("Désolé la date limite doit être supérieure à la date du jour");
				
			}
			
			return dtoTask;			
			
		}
		
		
		//Modif dateLimite d'une tâche
		@Override
		public boolean updateDate(long id, DtoUpdateDate dtoUpdateDate) {
			
			
			Optional<ETask> opt = taskRepository.findById(id);
			
			if (opt.isPresent()) {
				ETask eTask = opt.get();
				eTask.setDateLimite(dtoUpdateDate.getDateLimite());
				taskRepository.save(eTask);
				
				return true;
				
			}
			
			else {
				throw new NotFoundException("Désolé id inexistant");
			}
			
			
		}


		//Modif etat d'une tâche
		@Override
		public boolean updateEtat(long id, DtoUpdateEtat dtoUpdateEtat) {
			
			Optional<ETask> opt = taskRepository.findById(id);
			
			if (opt.isPresent()) {
				ETask eTask = opt.get();
				eTask.setEtat(dtoUpdateEtat.isEtat());
				taskRepository.save(eTask);
				
				return true;
				
			}
			
			else {
				throw new NotFoundException("Désolé id inexistant");
			}
		}


		//Modif label d'une tâche
		@Override
		public boolean updateLabel(long id, DtoUpdateLabel dtoUpdateLabel) {
			
			Optional<ETask> opt = taskRepository.findById(id);
			
			if (opt.isPresent()) {
				ETask eTask = opt.get();
				eTask.setLabel(dtoUpdateLabel.getLabel());
				taskRepository.save(eTask);
				
				return true;
				
			}
			
			else {
				throw new NotFoundException("Désolé id inexistant");
			}
		}


		//Modif projet d'une tâche
		@Override
		public boolean updateProjet(long id, DtoUpdateProjet dtoUpdateProjet) {
			
			Optional<ETask> opt = taskRepository.findById(id);
			
			if (opt.isPresent()) {
				ETask eTask = opt.get();
				eTask.seteProject(dtoUpdateProjet.geteProject());
				taskRepository.save(eTask);
				
				return true;
				
			}
			
			else {
				
				throw new NotFoundException("Désolé id inexistant");
			}
		}


		//Supprimer une tâche
		@Override
		public void deleteById(long id) {
			
			taskRepository.deleteById(id);
			
		}

}
