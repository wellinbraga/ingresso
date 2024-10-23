package br.com.fabricasoftwarehh.ingresso.services;

import br.com.fabricasoftwarehh.ingresso.domain.attendee.Attendee;
import br.com.fabricasoftwarehh.ingresso.domain.attendee.exception.AttendeeAlreadyExistException;
import br.com.fabricasoftwarehh.ingresso.domain.checkin.Checkin;
import br.com.fabricasoftwarehh.ingresso.domain.event.Event;
import br.com.fabricasoftwarehh.ingresso.dto.attendee.AttendeeDetails;
import br.com.fabricasoftwarehh.ingresso.dto.attendee.AttendeeIdDto;
import br.com.fabricasoftwarehh.ingresso.dto.attendee.AttendeeRequestDTO;
import br.com.fabricasoftwarehh.ingresso.dto.attendee.AttendeesListResponseDTO;
import br.com.fabricasoftwarehh.ingresso.repositories.AttendeeRepository;
import br.com.fabricasoftwarehh.ingresso.repositories.CheckinRepository;
import br.com.fabricasoftwarehh.ingresso.repositories.EventRepository;
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

    public Attendee registerAttendee(Attendee newAttendee) {
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isAttendeeRegistered.isPresent()) throw  new AttendeeAlreadyExistException("Attendee is already registered");
    }
}
