package dev.meetuco.homey_be.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	boolean existsByNameIgnoreCase(String name);
}
