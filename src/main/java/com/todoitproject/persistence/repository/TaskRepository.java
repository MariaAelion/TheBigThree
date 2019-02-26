package com.todoitproject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoitproject.persistence.entity.ETask;

public interface TaskRepository extends JpaRepository <ETask, Long>{

}
