package br.com.fabricasoftwarehh.pass_in.domain.event.exeptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message){
        super(message);
    }
}
