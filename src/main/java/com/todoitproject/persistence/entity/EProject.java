package com.todoitproject.persistence.entity;


/**
*  @author TheBigThree
* @version 1.0.0
*
*/



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name="t_project")

public class EProject {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name ="id", unique = true, nullable = false)
	private long id;
	
	@Column (name="nom", length = 250, nullable=false)
	private String nom;
	
	@Column (name="description", length = 250, nullable=false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable=true)
	private EUser eUser;
	
	@OneToMany(mappedBy="eProject", cascade = javax.persistence.CascadeType.ALL)
	private List<ETask> taches = new ArrayList<ETask>();

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

	public EUser geteUser() {
		return eUser;
	}

	public void seteUser(EUser eUser) {
		this.eUser = eUser;
	}

	public List<ETask> getTaches() {
		return taches;
	}

	public void setTaches(List<ETask> taches) {
		this.taches = taches;
	}


	


}
