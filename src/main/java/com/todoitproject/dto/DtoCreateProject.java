package com.todoitproject.dto;

import com.todoitproject.persistence.entity.EUser;

public class DtoCreateProject {

	private String nom;
	private String description;
	private EUser id_user;
	

	
	/**
	 * @return the id_user
	 */
	public EUser getId_user() {
		return id_user;
	}



	/**
	 * @param id_user the id_user to set
	 */
	public void setId_user(EUser id_user) {
		//this.id_user = id_user;
	}



	public DtoCreateProject() {
		super();
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


	
	
	
}
