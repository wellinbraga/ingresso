package br.com.fabricasoftwarehh.pass_in.dto.event;

public record EventRequestDTO(String title,
                              String details,
                              Integer maximumAttendees) {
}
