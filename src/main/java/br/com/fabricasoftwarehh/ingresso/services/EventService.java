package br.com.fabricasoftwarehh.ingresso.services;

import br.com.fabricasoftwarehh.ingresso.domain.attendee.Attendee;
import br.com.fabricasoftwarehh.ingresso.domain.event.Event;
import br.com.fabricasoftwarehh.ingresso.domain.event.exeptions.EventNotFoundException;
import br.com.fabricasoftwarehh.ingresso.dto.event.EventIdDTO;
import br.com.fabricasoftwarehh.ingresso.dto.event.EventRequestDTO;
import br.com.fabricasoftwarehh.ingresso.dto.event.EventResponseDTO;
import br.com.fabricasoftwarehh.ingresso.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;
    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event nota found with ID:" + eventId));
        List<Attendee> ateendeeList = this.attendeeService.getAllAttendeesFomEvent(eventId);
        return new EventResponseDTO(event, ateendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setDetails(eventDTO.details());
        newEvent.setTitle(eventDTO.title());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlung(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlung(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD); //CRIA UMA NOMALIZAÇÃO SEPARANDO ACENTOS EX. CÃO = CA~O
        return  normalized.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}","") //SELECIONA TODOS OS ACENTOS E SUBSTITUI POR UMA STRING VAZIA
                .replaceAll("[^\\w\s]","")
                .replaceAll("\\s+","-") //SUBSTITUI TODOS OS ESPAÇOS POR UM '-', \\S PARA ESPACO E + SE TIVER MAIS DE UM ESPAÇO
                .toLowerCase(); // CONVERTER TUDO PARA MINUSCULO
    }
}
