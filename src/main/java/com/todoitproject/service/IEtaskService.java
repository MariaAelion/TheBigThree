package com.todoitproject.service;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
import java.time.LocalDate;
import java.util.List;

import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoRTasks;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdatePriorite;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.exception.BeforeNowException;
import com.todoitproject.persistence.entity.ETask;

public interface IEtaskService {
	
	DtoTask save(long idUser, DtoTask dtoTask);
	
	boolean updateDate(long id, DtoUpdateDate dtoUpdateDate);
	
	boolean updateEtat(long id, DtoUpdateEtat dtoUpdateEtat);
	
	boolean updateLabel(long id, DtoUpdateLabel dtoUpdateLabel);
	
	boolean updateProjet(long id, DtoUpdateProjet dtoUpdateProjet);
	
	boolean updatePriorite(long id, DtoUpdatePriorite dtoUpdatePriorite);
	
	List<DtoRTasks> getAllTasks(List<DtoRProject> list);
	
	List<DtoRTasks> getAllTasksForADay(List<DtoRProject> list, LocalDate localDate);
	
	List<DtoRTasks> getAllTasksForAWeek(List<DtoRProject> list);
	
	void deleteById(long id);

}
