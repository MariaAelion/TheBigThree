package com.todoitproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.ILogService;

@Service
@Transactional
public class LogService implements ILogService{
	
	@Autowired UserRepository userRepository;

}
