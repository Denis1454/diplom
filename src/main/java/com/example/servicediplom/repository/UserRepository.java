package com.example.servicediplom.repository;

import com.example.servicediplom.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByIdIn(List<Long> ids, Pageable pageable);
}
