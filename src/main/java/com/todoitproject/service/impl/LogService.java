package com.todoitproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.persistence.repository.UserRepository;

@Service
@Transactional
public class LogService {
	
	@Autowired UserRepository userRepository;

}
