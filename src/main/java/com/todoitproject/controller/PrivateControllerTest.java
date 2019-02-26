package com.todoitproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoitproject.service.IGlobalService;

@CrossOrigin
@RestController
@RequestMapping(value="/private/test")
public class PrivateControllerTest {
	
	@Autowired IGlobalService iService;

}
