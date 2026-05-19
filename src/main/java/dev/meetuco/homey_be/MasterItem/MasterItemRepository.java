package dev.meetuco.homey_be.MasterItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterItemRepository extends JpaRepository<MasterItem, Long> {
  
}
