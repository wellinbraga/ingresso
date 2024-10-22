package br.com.fabricasoftwarehh.pass_in.dto.attendee;

import lombok.Getter;

import java.util.List;
public record AttendeesListResponseDTO (List<AttendeeDetails> attendees) {

}
