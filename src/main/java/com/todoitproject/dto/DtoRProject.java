package com.todoitproject.dto;

import com.todoitproject.persistence.entity.EProject;

public class DtoRProject {

	private String nom;
	private String description;

	public DtoRProject() {
		super();
	}

	public DtoRProject(EProject proj) {
			super();
			this.setNom(proj.getNom());
			this.setDescription(proj.getDescription());
					
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