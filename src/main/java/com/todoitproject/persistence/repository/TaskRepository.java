package com.todoitproject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.todoitproject.persistence.entity.ETask;
/**
*  @author TheBigThree
* @version 1.0.0
*
*/
public interface TaskRepository extends JpaRepository <ETask, Long>{
	

}
