package fr.fms.springbasics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.springbasics.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{	
}
