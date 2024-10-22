package br.com.fabricasoftwarehh.ingresso.dto.event;

public record EventDetailDTO (    String id,
                                  String tittle,
                                  String details,
                                  String slug,
                                  Integer maximumAttendees,
                                  Integer attendeesAmount
){

}

