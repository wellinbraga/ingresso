package br.com.fabricasoftwarehh.pass_in.dto.event;

public record EventDetailDTO (    String id,
                                  String tittle,
                                  String details,
                                  String slug,
                                  Integer maximumAttendees,
                                  Integer attendeesAmount
){

}

