package com.example.servicediplom.repository;

import com.example.servicediplom.entities.Event;
import com.example.servicediplom.entities.enums.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByCategoryIdInAndInitiatorIdInAndStateIn(List<Long> categories, List<Long> users, List<State> states, Pageable pageable);
    boolean existsEventsByCategoryId(Long categoryId);
    List<Event> findAllByInitiator_Id(Long user, Pageable pageable);
    Event findByInitiator_IdAndId(Long user, Long eventId);
}
