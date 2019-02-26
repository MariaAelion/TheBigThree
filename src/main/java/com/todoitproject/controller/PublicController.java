package com.todoitproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.service.ILogService;

@CrossOrigin
@RestController
@RequestMapping(value="/public")
public class PublicController {
	
	@Autowired ILogService iLogService;

}
