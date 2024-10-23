package br.com.fabricasoftwarehh.ingresso.domain.event.exeptions;

public class EventFullException extends RuntimeException {
    public EventFullException(String message){
        super(message);
    }
}
