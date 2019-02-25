package persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.entity.EProject;

public interface ProjectRepository extends JpaRepository <EProject, Long>{

}
