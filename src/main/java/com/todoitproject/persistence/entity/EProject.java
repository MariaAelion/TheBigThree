package com.todoitproject.persistence.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="t_projet")

public class EProject {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name="nom", length = 250, nullable=false)
	private String nom;
	
	@Column (name="description", length = 250, nullable=false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable=false)
	private EUser eUser;

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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the eUser
	 */
	public EUser geteUser() {
		return eUser;
	}

	/**
	 * @param eUser the eUser to set
	 */
	public void seteUser(EUser eUser) {
		this.eUser = eUser;
	}
	
	
	


}
