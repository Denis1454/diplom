package com.example.servicediplom.repository;

import com.example.servicediplom.entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByIdIn(List<Long> categories, Pageable pageable);
}
