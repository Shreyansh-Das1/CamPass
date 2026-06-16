package com.campusEvent.campus_event.repository;

import com.campusEvent.campus_event.entity.Event;
import com.campusEvent.campus_event.entity.User;
import com.campusEvent.campus_event.relations.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisRepo extends JpaRepository<Registration,Long> {
    boolean existsByUser(Long userId, long eventId);
    List<Registration> findByEvent(Event event); //Finds all Event attendees
    List<Registration> findByUser(User user); //Finds all events an user registered for
    Optional<Registration> findById(long id);
    Optional<Registration> findByUser_IdAndEvent_Eventid(Long userId, Long  eventId);
}
