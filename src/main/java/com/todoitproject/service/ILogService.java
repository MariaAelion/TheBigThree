package com.todoitproject.service;

import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;
import com.todoitproject.dto.DtoUserLog;

public interface ILogService {

	DtoRCreateUser createUser(DtoCreateUser dtoCreateUser);
	
	DtoUserLog findUserByLogPass(String login, String password);

	DtoUserLog findOne(long id);

	DtoUserLog DeleteOne(long id);

	boolean changeMail(long id, String mail);

	boolean changePassword(long id, String password);

}
