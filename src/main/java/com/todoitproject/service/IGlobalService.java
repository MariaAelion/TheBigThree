package com.todoitproject.service;

/**
*  @author TheBigThree
* @version 1.0.0
*
*/
import com.todoitproject.persistence.entity.EUser;

public interface IGlobalService {

	public EUser getUserByLog(long id);
	public boolean checkUserByLog(long id);
	

	
}
