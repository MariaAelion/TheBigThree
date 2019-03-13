package com.todoitproject.dto.ETask;

import java.time.LocalDate;
/**
 * @author TheBigThree
 * @version 1.0.1
 *
 */
public class DtoRTasks {

	private long  id;
	private String label;
	private LocalDate dateCrea;
	private LocalDate dateLimite;
	private int priorite;
	private boolean etat;
	private long id_projet;
	
	
	public DtoRTasks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DtoRTasks(long id, String label, LocalDate dateCrea, LocalDate dateLimite, int priorite, boolean etat,
			long id_projet) {
		super();
		this.id = id;
		this.label = label;
		this.dateCrea = dateCrea;
		this.dateLimite = dateLimite;
		this.priorite = priorite;
		this.etat = etat;
		this.id_projet = id_projet;
	}
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
	public long getId_projet() {
		return id_projet;
	}
	public void setId_projet(long id_projet) {
		this.id_projet = id_projet;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
