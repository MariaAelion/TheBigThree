package com.todoitproject.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoitproject.calcul.EncodePassTodoIt;
import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoRCreateUser;
import com.todoitproject.dto.DtoUserLog;
import com.todoitproject.exception.NotFoundException;
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.EUser;
import com.todoitproject.persistence.repository.ProjectRepository;
import com.todoitproject.persistence.repository.TaskRepository;
import com.todoitproject.persistence.repository.UserRepository;
import com.todoitproject.service.IEmailService;
import com.todoitproject.service.ILogService;
import com.todoitproject.service.IProjectService;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
@Service
@Transactional
public class LogService implements ILogService{
	
	@Autowired UserRepository userRepository;
	
	@Autowired IProjectService iProjectService;
	
	@Autowired ProjectRepository projectRepository;
	
	@Autowired TaskRepository taskRepository;
	
	@Autowired IEmailService iEmailService;


	@Override
	public DtoRCreateUser createUser(DtoCreateUser dtoCreateUser) {
		if(iEmailService.isValidEmailAddress(dtoCreateUser.getMail())) {
	
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
				iEmailService.sendMailCreateUser(dtoCreateUser);
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

	@Override
	public boolean changeMail(long id, String mail) {
		if(iEmailService.isValidEmailAddress(mail)) {
		 Optional<EUser> oEUser= userRepository.findById(id);
		 if (oEUser.isPresent()) {
			 EUser eUser = new EUser();
			 eUser = oEUser.get();
			 eUser.setMail(mail);
			 iEmailService.sendMailModifMail(eUser.getLogin(), mail);
			 userRepository.save(eUser);
			
		 } else {
			 throw new NotFoundException("L'utilisateur avec l'id" +id  +" n'est pas present dans la base de donnée");
		 }
		return true;	
		} else {
		return false;
		}
	}

	@Override
	public boolean changePassword(long id, String password) {
		Optional<EUser> oEUser= userRepository.findById(id);
		 if (oEUser.isPresent()) {
			 EUser eUser = new EUser();
			 eUser = oEUser.get();
			 eUser.setPassword(EncodePassTodoIt.passwordEncoder().encode(password));
			 userRepository.save(eUser);
			 iEmailService.sendMailModifPassword(eUser.getLogin(), eUser.getPassword(), eUser.getMail()); 
			 return true;
		 } else {
			 throw new NotFoundException("L'utilisateur avec l'id" +id  +" n'est pas present dans la base de donnée");
		 }
	}
	
	/// Acces repository
	
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
	

}
