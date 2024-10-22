package br.com.fabricasoftwarehh.pass_in.controllers;

import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeeDetails;
import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeeIdDto;
import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeeRequestDTO;
import br.com.fabricasoftwarehh.pass_in.dto.event.EventIdDTO;
import br.com.fabricasoftwarehh.pass_in.dto.event.EventRequestDTO;
import br.com.fabricasoftwarehh.pass_in.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeesController {

    private final AttendeeService attendeeService;
    @GetMapping
    public ResponseEntity<String> getTeste(){
        return ResponseEntity.ok("sucesso!");
    }

    @PostMapping
    public ResponseEntity<AttendeeIdDto> createAttendee(@RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDto attendeeIdDto =  this.attendeeService.createAttendee(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(attendeeIdDto.id()).toUri();
        return  ResponseEntity.created(uri).body(attendeeIdDto);
    }
}
