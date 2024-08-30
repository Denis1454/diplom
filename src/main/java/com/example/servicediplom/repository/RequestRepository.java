package com.example.servicediplom.repository;

import com.example.servicediplom.entities.Request;
import com.example.servicediplom.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    boolean existsByEventIdAndRequesterId(Long eventId, Long userId);
    List<Request> findAllByRequester(User user);
    List<Request> findAllByEvent_IdAndRequester(Long event, User user); //TODO Проверить как работает Спринг дата с Entity
    @Query(value = "SELECT COUNT(requester) FROM participation_request WHERE event = :eventId", nativeQuery = true)
    Long countAllByEvent_Id(@Param("eventId") Long eventId);
}
