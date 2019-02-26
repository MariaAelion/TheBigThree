package com.todoitproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	
	
	
	@Override
	public DtoTask save(DtoTask dtoTask) {
		
		ETask eTask = new ETask();
		eTask.setDateCrea(dtoTask.getDateCrea());
		eTask.setDateLimite(dtoTask.getDateLimite());
		eTask.setEtat(dtoTask.isEtat());
		eTask.seteProject(dtoTask.geteProject());
		eTask.setLabel(dtoTask.getLabel());
		eTask.setPriorite(dtoTask.getPriorite());
		
		taskRepository.save(eTask);
		
		return dtoTask;
	}

}
