package br.com.fabricasoftwarehh.ingresso.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeRequestDTO(String name,
                                 String event,
                                 String email,
                                 LocalDateTime createdAt
) {
}

