package com.todoitproject.service;

import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoMail;

public interface IEmailService {

	void sendMail(DtoMail mail);

	boolean isValidEmailAddress(String email);

	void sendMailCreateUser(DtoCreateUser dtoCreateUser);

	void sendMailModifMail(String login, String mail);

	void sendMailModifPassword(String login, String password, String mail);

}
