package persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.entity.EUser;


public interface UserRepository extends JpaRepository <EUser, Long>{

}
