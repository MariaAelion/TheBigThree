package com.todoitproject.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoRTasks;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.exception.BeforeNowException;
import com.todoitproject.exception.NotFoundException;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.TaskRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.IEtaskService;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
@Service
@Transactional
public class EtaskService implements IEtaskService{
	
	
	@Autowired TaskRepository taskRepository;
	@Autowired ProjectRepository projectRepository;
	@Autowired UserRepository userRepository;
	
	
		@Override
		public DtoTask save(long idUser, DtoTask dtoTask) {
			
			ETask eTask = new ETask();
			
			
			if (dtoTask.getDateLimite().isAfter(LocalDate.now())) {
				
				eTask.setDateLimite(dtoTask.getDateLimite());
				eTask.setDateCrea(LocalDate.now());
				dtoTask.setDateCrea(eTask.getDateCrea());
				eTask.setEtat(false);
				eTask.setLabel(dtoTask.getLabel());
				eTask.setPriorite(dtoTask.getPriorite());
				
				if (dtoTask.getIdProject() == -1) {
					Optional<EUser> oEUser = userRepository.findById(idUser);
					if (oEUser.isPresent()) {
						dtoTask.setIdProject(oEUser.get().getIdDefautProject());
					} else {
						throw new NotFoundException("Id Utilisateur inexistant");
					}
					
				} else {
					// Nothing to do
				}
				
				Optional<EProject> oeProject = projectRepository.findById(dtoTask.getIdProject());
			
				if (oeProject.isPresent()) {
					eTask.seteProject(oeProject.get());
					taskRepository.save(eTask);
				} else {
					throw new NotFoundException("Ce projet est inexistant");
				}
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


		@Override
		public List<DtoRTasks> getAllTasks(List<DtoRProject> list) {
			//Parcours de la liste DTOrProject pour trouver les id
			List<DtoRTasks> tasks = new ArrayList<DtoRTasks>();
		
			for (DtoRProject dtoRProject : list) {
				Long idLong = dtoRProject.getId();
				Optional<ETask> opt = taskRepository.findByIdProject(idLong);
				
				if (opt.isPresent()) {
						
				ETask eTask = new ETask();
				
				eTask = opt.get();
				
				DtoRTasks dtoRTasks = new DtoRTasks();
				
				dtoRTasks.setDateCrea(eTask.getDateCrea());
				dtoRTasks.setDateLimite(eTask.getDateLimite());
				dtoRTasks.setEtat(eTask.isEtat());
				dtoRTasks.setId_projet(eTask.geteProject().getId());
				dtoRTasks.setLabel(eTask.getLabel());
				dtoRTasks.setPriorite(eTask.getPriorite());
				
				tasks.add(dtoRTasks);
					
				}
				
			}
			
			return tasks;
		}


		@Override
		public List<DtoRTasks> getAllTasksForADay(List<DtoRProject> list, LocalDate localDate) {
			List<DtoRTasks> tasks = new ArrayList<DtoRTasks>();
			
			for (DtoRProject dtoRProject : list) {
				Long idLong = dtoRProject.getId();
				Optional<ETask> opt = taskRepository.findByIdAndDate(idLong, localDate);
				
				if (opt.isPresent()) {
						
				ETask eTask = new ETask();
				
				eTask = opt.get();
				
				DtoRTasks dtoRTasks = new DtoRTasks();
				
				dtoRTasks.setDateCrea(eTask.getDateCrea());
				dtoRTasks.setDateLimite(eTask.getDateLimite());
				dtoRTasks.setEtat(eTask.isEtat());
				dtoRTasks.setId_projet(eTask.geteProject().getId());
				dtoRTasks.setLabel(eTask.getLabel());
				dtoRTasks.setPriorite(eTask.getPriorite());
				
				tasks.add(dtoRTasks);
					
				}
				
			}
			
			return tasks;
			
		}


		@Override
		public List<DtoRTasks> getAllTasksForAWeek(List<DtoRProject> list) {
			
			List<DtoRTasks> tasks = new ArrayList<DtoRTasks>();
			
			for (DtoRProject dtoRProject : list) {
				Long idLong = dtoRProject.getId();
				Optional<ETask> opt = taskRepository.findByIdAndTwoDates(idLong, LocalDate.now(), LocalDate.now().plusDays(7));
				
				if (opt.isPresent()) {
						
				ETask eTask = new ETask();
				
				eTask = opt.get();
				
				DtoRTasks dtoRTasks = new DtoRTasks();
				
				dtoRTasks.setDateCrea(eTask.getDateCrea());
				dtoRTasks.setDateLimite(eTask.getDateLimite());
				dtoRTasks.setEtat(eTask.isEtat());
				dtoRTasks.setId_projet(eTask.geteProject().getId());
				dtoRTasks.setLabel(eTask.getLabel());
				dtoRTasks.setPriorite(eTask.getPriorite());
				
				tasks.add(dtoRTasks);
					
				}
				
			}
			
			return tasks;
		}

}
