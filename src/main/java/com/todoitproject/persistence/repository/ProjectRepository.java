package com.todoitproject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoitproject.persistence.entity.EProject;

public interface ProjectRepository extends JpaRepository <EProject, Long>{

}
