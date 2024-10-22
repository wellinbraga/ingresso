package br.com.fabricasoftwarehh.ingresso.repositories;

import br.com.fabricasoftwarehh.ingresso.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
