package com.todoitproject.service;

import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;

public interface ILogService {

	DtoRCreateUser createUser(DtoCreateUser dtoCreateUser);

}
