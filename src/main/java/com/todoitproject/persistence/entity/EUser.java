package com.todoitproject.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="t_utilisateur")
public class EUser {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name="login", length = 20, nullable=false)
	private String login;
	
	@Column (name="password", length = 20, nullable=false)
	private String password;
	
	@OneToMany
	@JoinColumn(name="id_user", referencedColumnName ="id")
	private List<ETask> etasks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public List<ETask> getEtasks() {
		return etasks;
	}

	public void setEtasks(List<ETask> etasks) {
		this.etasks = etasks;
	}
	
	
}
