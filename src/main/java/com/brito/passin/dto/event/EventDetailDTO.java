package com.brito.passin.dto.event;

public record EventDetailDTO(
        String id,
        String tittle,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer attendeesAmount
) {
}
