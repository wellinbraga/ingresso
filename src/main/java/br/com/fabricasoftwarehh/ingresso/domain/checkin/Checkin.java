package br.com.fabricasoftwarehh.ingresso.domain.checkin;

import br.com.fabricasoftwarehh.ingresso.domain.attendee.Attendee;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "check_ins")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_at")
    private LocalDateTime createAt;
    @OneToOne
    @JoinColumn(name = "attendee_id" , nullable = false)
    private Attendee attendee;
}
