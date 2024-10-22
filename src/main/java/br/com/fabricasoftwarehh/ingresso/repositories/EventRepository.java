package br.com.fabricasoftwarehh.pass_in.repositories;

import br.com.fabricasoftwarehh.pass_in.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
