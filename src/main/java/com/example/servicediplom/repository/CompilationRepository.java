package com.example.servicediplom.repository;

import com.example.servicediplom.entities.Compilation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation,Long> {
    Page<Compilation> findCompilationsByPinned(boolean pinned,Pageable pageable);
}
