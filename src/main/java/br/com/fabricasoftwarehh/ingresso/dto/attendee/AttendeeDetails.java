package br.com.fabricasoftwarehh.pass_in.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeDetails(String id,
                              String name,
                              String email,
                              LocalDateTime createdAt,
                              LocalDateTime checkInAt) {
}
