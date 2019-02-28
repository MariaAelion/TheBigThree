package com.todoitproject.service;



import com.todoitproject.dto.DtoTask;
import com.todoitproject.persistence.entity.EUser;

public interface IGlobalService {

	DtoTask save(DtoTask dtoTask);
	public EUser getUserByLog(long id);
	public boolean checkUserByLog(long id);

	
}
