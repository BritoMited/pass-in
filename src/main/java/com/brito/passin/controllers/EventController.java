package com.brito.passin.controllers;

import com.brito.passin.dto.attendee.AttendeeIdDTO;
import com.brito.passin.dto.attendee.AttendeeRequestDTO;
import com.brito.passin.dto.attendee.AttendeesListResponseDTO;
import com.brito.passin.dto.event.EventIdDTO;
import com.brito.passin.dto.event.EventRequestDTO;
import com.brito.passin.dto.event.EventResponseDTO;
import com.brito.passin.services.AttendeeService;
import com.brito.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){

        // nessa função, voce entra com o Id, ele procura no repository
        // me retornando então um new EventResponse com os detalhes do evento
        EventResponseDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        //o usuario me passa o body, nesse createEvent, ele vai salvar as informações no repository e o retorno dele é o
        //eventIdDTO, entao baseado nele que é criado a URI
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

       var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerPartcipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){

        // nessa função, voce entra com o Id, ele procura no repository
        // me retornando então um new EventResponse com os detalhes do evento
        AttendeesListResponseDTO attendeesListResponse = this.attendeeService.getEventAttendee(id);
        return ResponseEntity.ok(attendeesListResponse);
    }


}
