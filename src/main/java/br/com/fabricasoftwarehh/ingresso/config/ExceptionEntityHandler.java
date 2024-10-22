package br.com.fabricasoftwarehh.pass_in.config;

import br.com.fabricasoftwarehh.pass_in.domain.event.exeptions.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //Captura as exceções lançanda pelo controller
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class) // Este metodo lida com a exceção EventNotFoundException.class
    public ResponseEntity handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build(); // Troca o status do retorno de 500, que é generico para o 404 que corresponde a notFound
    }
}
