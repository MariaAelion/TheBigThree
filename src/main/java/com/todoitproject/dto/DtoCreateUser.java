package com.todoitproject.dto;
/**
*  @author TheBigThree
* @version 1.0.1
*/
public class DtoCreateUser {
	
	private String login;
	private String password;
	private String mail;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	

}
