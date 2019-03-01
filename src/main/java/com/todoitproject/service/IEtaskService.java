package com.todoitproject.service;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
import java.time.LocalDate;

import com.todoitproject.dto.DtoTask;
import com.todoitproject.dto.ETask.DtoUpdateDate;
import com.todoitproject.dto.ETask.DtoUpdateEtat;
import com.todoitproject.dto.ETask.DtoUpdateLabel;
import com.todoitproject.dto.ETask.DtoUpdateProjet;
import com.todoitproject.exception.BeforeNowException;

public interface IEtaskService {
	
	DtoTask save(DtoTask dtoTask);
	
	boolean updateDate(long id, DtoUpdateDate dtoUpdateDate);
	
	boolean updateEtat(long id, DtoUpdateEtat dtoUpdateEtat);
	
	boolean updateLabel(long id, DtoUpdateLabel dtoUpdateLabel);
	
	boolean updateProjet(long id, DtoUpdateProjet dtoUpdateProjet);
	
	void deleteById(long id);

}
