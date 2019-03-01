package com.todoitproject.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.calcul.EncodePassTodoIt;
import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoMail;
import com.todoitproject.dto.DtoMailAttributs;
import com.todoitproject.dto.DtoRCreateUser;
import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.exception.NotFoundException;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.TaskRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.ILogService;
import com.todoitproject.service.IProjectService;

@Service
@Transactional
public class LogService implements ILogService{
	
	@Autowired UserRepository userRepository;
	
	@Autowired IProjectService iProjectService;
	
	@Autowired ProjectRepository projectRepository;
	
	@Autowired TaskRepository taskRepository;
	
	@Autowired EmailService emailService;

	@Override
	public DtoRCreateUser createUser(DtoCreateUser dtoCreateUser) {
		if(EmailService.isValidEmailAddress(dtoCreateUser.getMail())) {
	
			DtoRCreateUser dtoRCreateUser = new DtoRCreateUser();
			
			if (!checkUserByLog(dtoCreateUser.getLogin())) {
				EUser eUser = new EUser();
				eUser.setLogin(dtoCreateUser.getLogin());
				eUser.setPassword(EncodePassTodoIt.passwordEncoder().encode(dtoCreateUser.getPassword()));
				eUser.setMail(dtoCreateUser.getMail());
				userRepository.save(eUser);
				
				if(this.checkUserByLog(dtoCreateUser.getLogin())) {
					eUser = this.getUserByLog(dtoCreateUser.getLogin());
					EProject eProject = new EProject();
					eProject.setNom("defaut");
					eProject.setDescription("Hello");
					eProject.seteUser(eUser);
					projectRepository.save(eProject);
					
					if(this.checkDefautProjectbyIdUser(eUser.getId())) {
						eProject = this.getDefautProjectbyIdUser(eUser.getId());
						eUser.setIdDefautProject(eProject.getId());
						userRepository.save(eUser);
					} else {
						throw new NotFoundException("le projet par defaut de l'utilisateur " + dtoCreateUser.getLogin() +"  a mal ete cree!");
					}

				} else {
					throw new NotFoundException("l'utilisateur a mal ete cree!");
				}
				
				
				
				dtoRCreateUser.setConfirm(true);
				this.sendMailUser(dtoCreateUser);
			} else {
				dtoRCreateUser.setConfirm(false);
			}
			return dtoRCreateUser;
			
		} else {
			
			throw new NotFoundException("le mail " + dtoCreateUser.getMail() + "n'est pas valide");
		}
		

	}

	@Override
	public DtoUserLog findUserByLogPass(String login, String password) {
		if(this.checkUserByLog(login)) {
			EUser eUser = this.getUserByLog(login);
			System.out.println("Correspondance password" + EncodePassTodoIt.passwordEncoder().matches(password, eUser.getPassword()));
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
	
	private boolean checkDefautProjectbyIdUser (long idUser) {
		if(projectRepository.findByNameAndUser("defaut", idUser).isPresent()) {
			return true;
		} else return false;
	}
	
	private EProject getDefautProjectbyIdUser (long idUser) {
		return projectRepository.findByNameAndUser("defaut", idUser).get();
	}
	
	private void sendMailUser(DtoCreateUser dtoCreateUser) {
	     DtoMail email = new DtoMail();
			email.setContent("Nous vous confirmons la creation de votre compte ToDoIT avec pour identifiant " + dtoCreateUser.getLogin() + " et pour password" + dtoCreateUser.getPassword());
			email.setFrom(DtoMailAttributs.FROM);
			email.setSubject(DtoMailAttributs.CONFIRMATIONSUBJECT);
			email.setTo(dtoCreateUser.getMail());
			emailService.sendMail(email);
	}

	@Override
	public DtoUserLog DeleteOne(long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			System.out.println("DESTRUCTION");
			
		} else {
			throw new NotFoundException("L'utilisateur avec l'id" +id  +" n'est pas present dans la base de donnée");
		}
		

		return null;
	}

}
