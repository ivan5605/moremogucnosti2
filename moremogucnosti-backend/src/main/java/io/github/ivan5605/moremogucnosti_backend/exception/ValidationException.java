package io.github.ivan5605.moremogucnosti_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Ova anotacija omogućuje globalno upravljanje iznimkama u kontroleru
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class) // Ova metoda će obraditi iznimke koje se javljaju kada validacija argumenta nije uspjela
    public ResponseEntity<Map<String, String>> handValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>(); // Stvara mapu za pohranu grešaka

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage()); // Dodaje grešku u mapu s imenom polja kao ključem i porukom greške kao vrijednošću
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
