package com.todoitproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
