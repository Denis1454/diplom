package com.example.servicediplom.repository;

import com.example.servicediplom.entities.Compilation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation,Long> {
}
