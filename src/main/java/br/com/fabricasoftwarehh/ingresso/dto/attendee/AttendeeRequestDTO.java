package br.com.fabricasoftwarehh.pass_in.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeRequestDTO(String name,
                                 String event,
                                 String email,
                                 LocalDateTime createdAt
) {
}

