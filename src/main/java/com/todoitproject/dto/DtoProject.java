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
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	
	
}
