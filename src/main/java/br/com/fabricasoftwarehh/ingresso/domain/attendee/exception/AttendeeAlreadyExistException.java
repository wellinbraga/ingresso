package br.com.fabricasoftwarehh.ingresso.domain.attendee.exception;

public class AttendeeAlreadyExistException extends RuntimeException{
    public AttendeeAlreadyExistException(String message){
        super(message);
    }
}
