package com.todoitproject.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.todoitproject.persistence.entity.ETask;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
public interface TaskRepository extends JpaRepository <ETask, Long> {
	


	@Query(value= "SELECT * FROM t_tache WHERE id_projet = ?1 AND dateLimite = ?2", nativeQuery = true)
	List<ETask> findByIdAndDate(long id_projet, LocalDate dateLimite);
	
	@Query(value= "SELECT * FROM t_tache WHERE id_projet = ?1 AND dateLimite BETWEEN ?2 AND ?3", nativeQuery = true)
	List<ETask> findByIdAndTwoDates(long id_projet, LocalDate dateLimite1, LocalDate dateLimite2);

	@Query(value= "SELECT * FROM t_tache WHERE id_projet = ?1", nativeQuery = true)
    List<ETask> findByIdProject(long id_projet);

}
