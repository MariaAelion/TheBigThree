package com.todoitproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;
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

}
