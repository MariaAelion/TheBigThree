package com.todoitproject.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoitproject.persistence.entity.EUser;


public interface UserRepository extends JpaRepository <EUser, Long>{

}
