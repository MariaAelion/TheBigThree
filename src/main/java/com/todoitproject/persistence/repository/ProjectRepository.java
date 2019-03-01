package com.todoitproject.persistence.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.todoitproject.persistence.entity.EProject;



public interface ProjectRepository extends JpaRepository <EProject, Long>{
	


	// chercher un projet par nom et par utilisateur, à voir peut etre en front
	
	@Query(value= "SELECT * FROM t_projet WHERE nom = ?1 AND id_user = ?2", nativeQuery = true)
	Optional<EProject> findByNameAndUser(String nom, long id_user);
	
// chercher list projets par utilisateur
	
	@Query(value="SELECT * FROM t_projet WHERE id_user = ?1", nativeQuery = true)
	List<EProject> findByUser(long id_user);
	
	

	

}
