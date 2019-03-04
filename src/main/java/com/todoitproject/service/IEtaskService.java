package com.todoitproject.service;

import java.time.LocalDate;
import java.util.List;

import com.todoitproject.dto.DtoRProject;
import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoRTasks;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.exception.BeforeNowException;
import com.todoitproject.persistence.entity.ETask;

public interface IEtaskService {
	
	DtoTask save(DtoTask dtoTask);
	
	boolean updateDate(long id, DtoUpdateDate dtoUpdateDate);
	
	boolean updateEtat(long id, DtoUpdateEtat dtoUpdateEtat);
	
	boolean updateLabel(long id, DtoUpdateLabel dtoUpdateLabel);
	
	boolean updateProjet(long id, DtoUpdateProjet dtoUpdateProjet);
	
	List<DtoRTasks> getAllTasks(List<DtoRProject> list);
	
	List<DtoRTasks> getAllTasksForADay(List<DtoRProject> list, LocalDate localDate);
	
	void deleteById(long id);

}
