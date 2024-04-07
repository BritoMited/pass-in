package com.brito.passin.services;

import com.brito.passin.domain.attendee.Attendee;
import com.brito.passin.domain.event.Event;
import com.brito.passin.domain.event.exceptions.EventNotFoundException;
import com.brito.passin.dto.event.EventIdDTO;
import com.brito.passin.dto.event.EventRequestDTO;
import com.brito.passin.dto.event.EventResponseDTO;
import com.brito.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;


    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        //esse return funciona por conta de que, quando essa classe for instanciada
        //o unico atributo dela é de dependencia do EventDetail, então essa é a unica coisa que ele pode
        // retornar baseado neste construtor
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();

        //aqui ele pega a request feita ao usuario
        //é feita a atualização no repository
        // apenas com essas informações porque o resto é automatico

        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        //e aqui ele cria um new EventId para criar o Id mais especifico,
        // baseado num Id criado automaticamente
        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

}
