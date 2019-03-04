package com.todoitproject.dto;

/**
*  @author TheBigThree
* @version 1.0.0
*
*/
import com.todoitproject.persistence.entity.EProject;

public class DtoRProject {


	private long id;



	private String nom;
	private String description;

	public DtoRProject() {
		super();
	}

	public DtoRProject(EProject proj) {

		super();
		this.setId(proj.getId());
		this.setNom(proj.getNom());
		this.setDescription(proj.getDescription());

	}

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

	

	public void setId(Long id) {
		this.id = id;
	}
	
	

}