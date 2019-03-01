package com.todoitproject.dto;

import java.time.LocalDate;

import com.todoitproject.persistence.entity.EProject;
import com.todoitproject.persistence.entity.EUser;

public class DtoTask {
	
	private String label;
	private LocalDate dateCrea;
	private LocalDate dateLimite;
	private int priorite;
	private boolean etat;
	private EProject eProject;
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
	public EProject geteProject() {
		return eProject;
	}
	public void seteProject(EProject eProject) {
		this.eProject = eProject;
	}
	
	

}
