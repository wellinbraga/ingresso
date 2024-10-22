package br.com.fabricasoftwarehh.pass_in.services;

import br.com.fabricasoftwarehh.pass_in.domain.attendee.Attendee;
import br.com.fabricasoftwarehh.pass_in.domain.checkin.Checkin;
import br.com.fabricasoftwarehh.pass_in.domain.event.Event;
import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeeDetails;
import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeeIdDto;
import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeeRequestDTO;
import br.com.fabricasoftwarehh.pass_in.dto.attendee.AttendeesListResponseDTO;
import br.com.fabricasoftwarehh.pass_in.dto.event.EventIdDTO;
import br.com.fabricasoftwarehh.pass_in.dto.event.EventRequestDTO;
import br.com.fabricasoftwarehh.pass_in.repositories.AttendeeRepository;
import br.com.fabricasoftwarehh.pass_in.repositories.CheckinRepository;
import br.com.fabricasoftwarehh.pass_in.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final EventRepository eventRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFomEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFomEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkin = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkin.<LocalDateTime>map(Checkin::getCreateAt).orElse(null);
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(),attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

    public AttendeeIdDto createAttendee(AttendeeRequestDTO body) {
        Attendee newAttendee = new Attendee();
        newAttendee.setName(body.name());
        newAttendee.setEmail(body.email());

        Optional<Event> event = this.eventRepository.findById(body.event());
        Event eventCreate = event.orElse(null);
        newAttendee.setEvent(eventCreate);

        newAttendee.setCreatedAt(LocalDateTime.now());

        this.attendeeRepository.save(newAttendee);

        return new AttendeeIdDto(newAttendee.getId());
    }
}
