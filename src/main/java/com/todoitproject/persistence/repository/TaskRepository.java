package com.todoitproject.persistence.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import com.todoitproject.persistence.entity.ETask;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
public interface TaskRepository extends JpaRepository <ETask, Long>{
	

	@Query(value= "SELECT * FROM t_tache WHERE id_projet = ?1", nativeQuery = true)
	Optional<ETask> findByIdProject(long id_projet);
	
	@Query(value= "SELECT * FROM t_tache WHERE id_projet = ?1 AND dateLimite = ?2", nativeQuery = true)
	Optional<ETask> findByIdAndDate(long id_projet, LocalDate dateLimite);


}
