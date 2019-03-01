package com.todoitproject.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoTask;
import com.todoitproject.persistence.entity.ETask;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.TaskRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.IGlobalService;

@Service
@Transactional
public class GlobalService implements IGlobalService {
	
	@Autowired ProjectRepository projectRepository;
	@Autowired TaskRepository taskRepository;
	@Autowired UserRepository userRepository;
	
	
	

	private EUser getUserByLog(long id) {
		
		Optional<EUser> oeUser = userRepository.findUserById(id);
		
		if (oeUser.isPresent()) {
			return oeUser.get();
			
		} else {
			throw new com.todoitproject.exception.NotFoundException ("Cet utilisateur n'existe pas");
		}
		

	}


	

}
