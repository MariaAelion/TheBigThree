package persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.entity.ETask;

public interface TaskRepository extends JpaRepository <ETask, Long>{

}
