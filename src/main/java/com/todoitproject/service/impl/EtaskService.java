package com.todoitproject.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoRTasks;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdatePriorite;
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
* @version 1.0.1
*
*/
@Service
@Transactional
public class EtaskService implements IEtaskService{
	
	
	@Autowired TaskRepository taskRepository;
	@Autowired ProjectRepository projectRepository;
	@Autowired UserRepository userRepository;
	
	/**
	 * @description creation d'une tache
	 * @parma idUser, dtoTAsk
	 * @return un dtoTask
	 */
		@Override
		public DtoTask save(long idUser, DtoTask dtoTask) {
			
			ETask eTask = new ETask();
			
			
			if (dtoTask.getDateLimite().isAfter(LocalDate.now().minusDays(1))) {
				
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
		
		
		/**
		 * @description modification de la date d'une tache
		 * @param id, dtoUpdateDate
		 * @return boolean true=ok
		 */
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
		
		
		/**
		 * @description modification de la priorite d'une tache
		 * @param id, dtoUpdatePriorite
		 * @return boolean true=ok
		 */
		
		@Override
		public boolean updatePriorite(long id, DtoUpdatePriorite dtoUpdatePriorite) {
			Optional<ETask> opt = taskRepository.findById(id);
			
			if (opt.isPresent()) {
				ETask eTask = opt.get();
				eTask.setPriorite(dtoUpdatePriorite.getPriorite());
				taskRepository.save(eTask);
				
				return true;
			}
			
			else {
				throw new NotFoundException("Désolé id inexistant");
			}
		}

		
		/**
		 * @description modification d'etat d'une tache
		 * @param id, dtoUpdateEtt
		 * @return boolean true=ok
		 */
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


		
		/**
		 * @description modification du label d'une tache
		 * @param id, dtoUpdateLabel
		 * @return boolean true=ok
		 */
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


		
		/**
		 * @description modification du projet d'une tache
		 * @param id, dtoUpdateProjet
		 * @return boolean true=ok
		 */
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


		
		/**
		 * @description suppression d'une tache
		 * @param id
		 */
		@Override
		public void deleteById(long id) {
			
			taskRepository.deleteById(id);
			
		}

/*
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
		}*/

	/**
	 * @description lister toutes les taches du jour
	 * @param date du jour , liste de dtoRproject
	 * @return une liste de tache dtoRtasks
	 */
		@Override
		public List<DtoRTasks> getAllTasksForADay(List<DtoRProject> list, LocalDate localDate) {
			List<DtoRTasks> tasks = new ArrayList<DtoRTasks>();
			
			for (DtoRProject dtoRProject : list) {
				Long idLong = dtoRProject.getId();
				List<ETask> etasks = taskRepository.findByIdAndDate(idLong, localDate);
				
				for (ETask task : etasks) {
					DtoRTasks dtoRTasks = new DtoRTasks();
					dtoRTasks.setId(task.getId());
					dtoRTasks.setDateCrea(task.getDateCrea());
					dtoRTasks.setDateLimite(task.getDateLimite());
					dtoRTasks.setEtat(task.isEtat());
					dtoRTasks.setId_projet(task.geteProject().getId());
					dtoRTasks.setLabel(task.getLabel());
					dtoRTasks.setPriorite(task.getPriorite());
					
					tasks.add(dtoRTasks);
				}
				
				
					
				
				
			}
			
			return tasks.stream().map(a -> new DtoRTasks(a.getId(), a.getLabel(), a.getDateCrea(), a.getDateLimite(), a.getPriorite(), a.isEtat(),
					a.getId_projet()))
					
				.sorted((b1, b2) -> b1.getDateLimite().compareTo(b2.getDateLimite()))
				.collect(Collectors.toList());
			
			
				
			
			//return tasks;
			
			/*
			 * 
			 * projets.stream().map(a -> new DtoRProject(a))
				.sorted((b1, b2) -> b1.getNom().compareTo(b2.getNom()))
				.collect(Collectors.toList());
			 * 
			 * */
			
			
		}

		/**
		 * @description lister toutes les taches de la semaine
		 * @return une liste de tache dtoRtasks
		 */
		@Override
		public List<DtoRTasks> getAllTasksForAWeek(List<DtoRProject> list) {
			
			List<DtoRTasks> tasks = new ArrayList<DtoRTasks>();
			
			for (DtoRProject dtoRProject : list) {
				Long idLong = dtoRProject.getId();
				List<ETask> etasks = taskRepository.findByIdAndTwoDates(idLong, LocalDate.now(), LocalDate.now().plusDays(7));
				
				for (ETask task : etasks) {
					
					DtoRTasks dtoRTasks = new DtoRTasks();
					dtoRTasks.setId(task.getId());	
					dtoRTasks.setDateCrea(task.getDateCrea());
					dtoRTasks.setDateLimite(task.getDateLimite());
					dtoRTasks.setEtat(task.isEtat());
					dtoRTasks.setId_projet(task.geteProject().getId());
					dtoRTasks.setLabel(task.getLabel());
					dtoRTasks.setPriorite(task.getPriorite());
					
					tasks.add(dtoRTasks);
					
				}
				
				
			}
			
            return tasks.stream().map(a -> new DtoRTasks(a.getId(), a.getLabel(), a.getDateCrea(), a.getDateLimite(), a.getPriorite(), a.isEtat(),
					a.getId_projet()))
					
				.sorted((b1, b2) -> b1.getDateLimite().compareTo(b2.getDateLimite()))
				.collect(Collectors.toList());
		}
		
		
		/**
		 * @description lister toutes les taches du jour
		 * @return une liste de tache dtoRtasks
		 */
        @Override
        public List<DtoRTasks> getAllTasks(List<DtoRProject> list) {

            List<DtoRTasks> tasks = new ArrayList<DtoRTasks>();

            for (DtoRProject dtoRProject : list) {
                Long idLong = dtoRProject.getId();
                List<ETask> opt = new ArrayList<ETask>();
                opt = taskRepository.findByIdProject(idLong);

                for (ETask optional : opt) {




                        DtoRTasks dtoRTasks = new DtoRTasks();
                        dtoRTasks.setId(optional.getId());
                        dtoRTasks.setDateCrea(optional.getDateCrea());
                        dtoRTasks.setDateLimite(optional.getDateLimite());
                        dtoRTasks.setEtat(optional.isEtat());
                        dtoRTasks.setId_projet(optional.geteProject().getId());
                        dtoRTasks.setLabel(optional.getLabel());
                        dtoRTasks.setPriorite(optional.getPriorite());

                        tasks.add(dtoRTasks);


                }



            }

            return tasks.stream().map(a -> new DtoRTasks(a.getId(), a.getLabel(), a.getDateCrea(), a.getDateLimite(), a.getPriorite(), a.isEtat(),
					a.getId_projet()))
					
				.sorted((b1, b2) -> b1.getDateLimite().compareTo(b2.getDateLimite()))
				.collect(Collectors.toList());
        }


		

}
