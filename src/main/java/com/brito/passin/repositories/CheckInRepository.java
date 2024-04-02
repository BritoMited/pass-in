package com.brito.passin.repositories;

import com.brito.passin.domain.checkin.CheckIn;
import com.brito.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, String> {
}
