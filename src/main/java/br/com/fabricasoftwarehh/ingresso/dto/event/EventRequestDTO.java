package br.com.fabricasoftwarehh.ingresso.dto.event;

public record EventRequestDTO(String title,
                              String details,
                              Integer maximumAttendees) {
}
