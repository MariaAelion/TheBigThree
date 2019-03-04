package com.todoitproject.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="t_utilisateur")
public class EUser {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name="login", length = 250, nullable=false)
	private String login;
	
	@Column (name="password", length = 250, nullable=false)
	private String password;
	
	@Column (name ="idDefautProject", unique = true, nullable = true)
	private long idDefautProject;
	
	@Column (name="mail", length = 250, nullable=false)
	private String mail;
	
	@OneToMany(mappedBy="eUser", cascade = CascadeType.ALL)
	private List<EProject> projects = new ArrayList<EProject>();

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public long getIdDefautProject() {
		return idDefautProject;
	}

	public void setIdDefautProject(long idDefautProject) {
		this.idDefautProject = idDefautProject;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	
}
