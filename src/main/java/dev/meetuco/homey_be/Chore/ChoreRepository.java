package dev.meetuco.homey_be.Chore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoreRepository extends JpaRepository<ChoreEntity, Long>{
  
}
