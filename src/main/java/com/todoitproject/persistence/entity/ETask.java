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
	
	
	
	

}
