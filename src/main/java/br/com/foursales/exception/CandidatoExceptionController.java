package br.com.foursales.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CandidatoExceptionController {
	
	// Nesse caso, poderiam ser verificadas diversas exceções 
	// Para critério de exemplo estou verificando HTTP Status 404
	
	@ExceptionHandler(value = CartaoCreditoNotfoundException.class)
	public ResponseEntity<Object> exception(CartaoCreditoNotfoundException exception) {
		return new ResponseEntity<>("Candidato não encontrado", HttpStatus.NOT_FOUND);
	}
}
