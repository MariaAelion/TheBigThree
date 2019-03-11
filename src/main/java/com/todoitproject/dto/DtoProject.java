package com.todoitproject.dto;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.EUser;

public class DtoProject {

	private String nom;
	private String description;
	private long id_user;
	
	
	public DtoProject() {
		super();}
	
	public DtoProject(EProject proj) {
		super();
		
		this.setNom(proj.getNom());
		
		// this.setId_user(getUserByLog);
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
	 * @return the id_user
	 */
	public long getId_user() {
		return id_user;
	}
	/**
	 * @param id_user the id_user to set
	 */
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}
	

	
	
	
}
