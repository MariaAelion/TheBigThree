package com.todoitproject.persistence.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.todoitproject.persistence.entity.EProject;



public interface ProjectRepository extends JpaRepository <EProject, Long>{
	
// Creer un nouveau projet, à voir utilisateur
	@Query(value= "SELECT * FROM t_project", nativeQuery = true)
	List<EProject> findAll();
	
	// chercher un projet par nom et par utilisateur, à voir peut etre en front
	@Query(value= "SELECT * FROM t_project WHERE nom = ?1 AND id_user = ?2", nativeQuery = true)
	List<EProject> findByNameAndUser(String nom, long id_user);
	
	
	// Supprimer un projet par nom
	@Query(value= "DELETE FROM t_project WHERE nom=?1", nativeQuery = true)
	EProject deleteProject(String nom);
	
	
	
	//@Modifying(clearAutomatically = true)
	//@Query(value= "UPDATE t_project SET  WHERE id_user = ?1", nativeQuery = true)
//	List<EProject> findById(long id);
	
//	@Modifying(clearAutomatically = true)
  //  @Query (value = "UPDATE t_reservation SET etatReservationClient = 'Annulé' WHERE id = ?1", nativeQuery = true)
    //void cancelReservation(long id);

}
