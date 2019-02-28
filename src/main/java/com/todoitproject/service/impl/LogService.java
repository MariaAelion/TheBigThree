package com.todoitproject.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.calcul.EncodePassTodoIt;
import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;
import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.exception.NotFoundException;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.ILogService;

@Service
@Transactional
public class LogService implements ILogService{
	
	@Autowired UserRepository userRepository;

	@Override
	public DtoRCreateUser createUser(DtoCreateUser dtoCreateUser) {
		DtoRCreateUser dtoRCreateUser = new DtoRCreateUser();
		if (!checkUserByLog(dtoCreateUser.getLogin())) {
			EUser eUser = new EUser();
			eUser.setLogin(dtoCreateUser.getLogin());
			eUser.setPassword(EncodePassTodoIt.passwordEncoder().encode(dtoCreateUser.getPassword()));
			userRepository.save(eUser);
			dtoRCreateUser.setConfirm(true);
		} else {
			dtoRCreateUser.setConfirm(false);
		}
		return dtoRCreateUser;
	}

	@Override
	public DtoUserLog findUserByLogPass(String login, String password) {
		if(this.checkUserByLog(login)) {
			EUser eUser = this.getUserByLog(login);
			System.out.println(EncodePassTodoIt.passwordEncoder().matches(password, eUser.getPassword()));
			if (EncodePassTodoIt.passwordEncoder().matches(password, eUser.getPassword())) {
				DtoUserLog dtoUserLog = new DtoUserLog();
				dtoUserLog.setId(eUser.getId());
				return dtoUserLog;				
			} else {	
				throw new NotFoundException("le mot de passe ne correspond pas!");
			}
		} else {	
			throw new NotFoundException("L'utilisateur " + login + " n'existe pas");
		}
	}

	@Override
	public DtoUserLog findOne(long id) {
		Optional<EUser> optEUser = userRepository.findById(id);
		if(optEUser.isPresent()) {
			DtoUserLog dtoUserLog = new DtoUserLog();
			dtoUserLog.setId(optEUser.get().getId());
			return dtoUserLog;
		} else {
			throw new NotFoundException("L'utilisateur avec l'id" +id  +" n'est pas present dans la base de donnée");
		}
	}
	
	private boolean checkUserByLog(String log) {
		if (userRepository.findUserByLog(log).isPresent())
		{return true;} else {return false;}
	}
	
	private EUser getUserByLog(String log) {
		return userRepository.findUserByLog(log).get();
	}

}
