package com.todoitproject.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_projet")

public class EProject {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name="nom", length = 20, nullable=false)
	private String nom;
	
	@Column (name="description", length = 20, nullable=false)
	private String description;
	
	@OneToMany
	@JoinColumn(name="id_projet", referencedColumnName ="id")
	private List<ETask> etasks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public List<ETask> getEtasks() {
		return etasks;
	}

	public void setEtasks(List<ETask> etasks) {
		this.etasks = etasks;
	}
	
	


}
