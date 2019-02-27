package com.todoitproject.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;
import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.ILogService;

@Service
@Transactional
public class LogService implements ILogService{
	
	@Autowired UserRepository userRepository;

	@Override
	public DtoRCreateUser createUser(DtoCreateUser dtoCreateUser) {
		
		// TODO verifier Utilisateur
		DtoRCreateUser dtoRCreateUser = new DtoRCreateUser();
		
		if (!checkUserByLog(dtoCreateUser.getLogin())) {
			
			EUser eUser = new EUser();
			eUser.setLogin(dtoCreateUser.getLogin());
			eUser.setPassword(dtoCreateUser.getPassword());
			userRepository.save(eUser);
			dtoRCreateUser.setConfirm(true);
			
		} else {
			dtoRCreateUser.setConfirm(false);
			
		}
		

		
		return dtoRCreateUser;
	}
	
	private boolean checkUserByLog(String log) {
		if (userRepository.findUserByPassword(log).isPresent())
		{return true;} else {return false;}
	}

	@Override
	public DtoUserLog findUserByLogPass(String login, String password) {
		
		// TODO Auto-generated method stub
		Optional<EUser> optEUser = userRepository.findUserByLogPass(login, password);
		if(optEUser.isPresent()) {
			DtoUserLog dtoUserLog = new DtoUserLog();
			dtoUserLog.setId(optEUser.get().getId());
			return dtoUserLog;
		} else {
			throw new com.todoitproject.exception.NotFoundException("L'utilisateur " + login + " ou le mot de passe ne correspond pas!");
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
			throw new com.todoitproject.exception.NotFoundException("L'utilisateur avec l'id" +id  +" n'est pas present dans la base de donnée");
		}
	}

}
