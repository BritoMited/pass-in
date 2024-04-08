package com.brito.passin.repositories;

import com.brito.passin.domain.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeReposity extends JpaRepository<Attendee, String> {
    List<Attendee> findByEventId(String eventId);

    Optional<Attendee> findByEventIdAndEmail(String eventId, String email);
}
