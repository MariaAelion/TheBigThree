package com.todoitproject.service;




import com.todoitproject.persistence.entity.EUser;

public interface IGlobalService {

	public EUser getUserByLog(long id);
	public boolean checkUserByLog(long id);
	

	
}
