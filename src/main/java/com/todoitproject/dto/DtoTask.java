package com.todoitproject.dto;
/**
*  @author TheBigThree
* @version 1.0.1
*
*/
import java.time.LocalDate;




public class DtoTask {
	
	private long id;
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
	private String label;
	private LocalDate dateCrea;
	private LocalDate dateLimite;
	private int priorite;
	private boolean etat;

	private long idProject;

	
	

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public LocalDate getDateCrea() {
		return dateCrea;
	}
	public void setDateCrea(LocalDate dateCrea) {
		this.dateCrea = dateCrea;
	}
	public LocalDate getDateLimite() {
		return dateLimite;
	}
	public void setDateLimite(LocalDate dateLimite) {
		this.dateLimite = dateLimite;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	public long getIdProject() {
		return idProject;
	}
	public void setIdProject(long idProject) {
		this.idProject = idProject;
	}
	
	
	
	

}
