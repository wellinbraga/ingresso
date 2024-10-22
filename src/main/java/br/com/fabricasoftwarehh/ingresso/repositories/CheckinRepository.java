package br.com.fabricasoftwarehh.pass_in.repositories;

import br.com.fabricasoftwarehh.pass_in.domain.checkin.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, String> {
    Optional<Checkin> findByAttendeeId(String attendeeId);

}
