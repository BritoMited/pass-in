package com.brito.passin.repositories;

import com.brito.passin.domain.attendee.Attendee;
import com.brito.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeReposity extends JpaRepository<Attendee, String> {
    List<Attendee> findByEventId(String eventId);
}
