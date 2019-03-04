package com.todoitproject.dto.ETask;

import java.time.LocalDate;

public class DtoRTasks {

	private String label;
	private LocalDate dateCrea;
	private LocalDate dateLimite;
	private int priorite;
	private boolean etat;
	private long id_projet;
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
	
	
	
}
