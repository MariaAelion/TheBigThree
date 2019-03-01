package com.todoitproject.service.impl;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.todoitproject.dto.DtoCreateUser;
import com.todoitproject.dto.DtoMail;
import com.todoitproject.dto.DtoMailAttributs;
import com.todoitproject.service.IEmailService;


@Service
@Transactional
public class EmailService implements IEmailService{
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(DtoMail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        emailSender.send(message);
    }
    
    @Override
    public boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    	      result = false;
    	   }
    	   return result;
    	}
    
    @Override
	public void sendMailCreateUser(DtoCreateUser dtoCreateUser) {
	     DtoMail email = new DtoMail();
			email.setContent("Nous vous confirmons la creation de votre compte ToDoIT avec pour identifiant " + dtoCreateUser.getLogin() + " et pour password" + dtoCreateUser.getPassword());
			email.setFrom(DtoMailAttributs.FROM);
			email.setSubject(DtoMailAttributs.CONFIRMATIONSUBJECT);
			email.setTo(dtoCreateUser.getMail());
			this.sendMail(email);
	}
	
    @Override
	public void sendMailModifMail(String login, String mail) {
	     DtoMail email = new DtoMail();
			email.setContent("Bonjour" + login + "Nous vous confirmons le changement de votre email");
			email.setFrom(DtoMailAttributs.FROM);
			email.setSubject(DtoMailAttributs.MODIFICATIONSUBJECT);
			email.setTo(mail);
			this.sendMail(email);
	}
	
    @Override
	public void sendMailModifPassword(String login, String password, String mail) {
	     DtoMail email = new DtoMail();
			email.setContent("Bonjour" + login + "Votre nouveau mot de passe est" + password );
			email.setFrom(DtoMailAttributs.FROM);
			email.setSubject(DtoMailAttributs.MODIFICATIONSUBJECT);
			email.setTo(mail);
			this.sendMail(email);
	}
	
    
    
    
}