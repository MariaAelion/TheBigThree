package com.todoitproject.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.todoitproject.persistence.entity.EUser;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/

public interface UserRepository extends JpaRepository <EUser, Long>{
	
	@Query(value= "SELECT * FROM t_utilisateur WHERE login = ?1", nativeQuery = true)
	Optional<EUser> findUserByLog(String log);
	
	@Query(value= "SELECT * FROM t_utilisateur WHERE id = ?1", nativeQuery = true)
	Optional<EUser> findUserById(long id);
	
	@Query(value= "SELECT * FROM t_utilisateur WHERE login = ?1 AND password = ?2", nativeQuery = true)
	Optional<EUser> findUserByLogPass(String log, String pass);

}
