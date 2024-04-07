package com.brito.passin.services;

import com.brito.passin.domain.attendee.Attendee;
import com.brito.passin.domain.checkin.CheckIn;
import com.brito.passin.dto.attendee.AttendeeDetails;
import com.brito.passin.dto.attendee.AttendeesListResponseDTO;
import com.brito.passin.repositories.AttendeeReposity;
import com.brito.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeReposity attendeeReposity;
    private final CheckInRepository checkInRepository;

    // o respostavel por consumir cada repositorio é
    // o service de tal repository
    public List<Attendee> getAllAttendeesFromEvent(String eventId){
       return this.attendeeReposity.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(),
                    attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

}
