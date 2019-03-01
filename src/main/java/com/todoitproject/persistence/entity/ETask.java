package com.todoitproject.persistence.entity;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_tache")
public class ETask {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name="label", nullable=false)
	private String label;
	
	@Column(name = "dateCrea", nullable = false)
	private LocalDate dateCrea;
	
	@Column(name = "dateLimite", nullable = false)
	private LocalDate dateLimite;
	
	@Column(name = "priorite", nullable = false)
	private int priorite;
	
	@Column(name = "etat", nullable = false)
	private boolean etat;
	
	@ManyToOne
	@JoinColumn(name="id_projet", nullable=true)
	private EProject eProject;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public EProject geteProject() {
		return eProject;
	}

	public void seteProject(EProject eProject) {
		this.eProject = eProject;
	}

	
	
	

}
