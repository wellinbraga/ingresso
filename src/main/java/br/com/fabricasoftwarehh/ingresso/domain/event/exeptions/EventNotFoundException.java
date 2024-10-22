package br.com.fabricasoftwarehh.ingresso.domain.event.exeptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message){
        super(message);
    }
}
