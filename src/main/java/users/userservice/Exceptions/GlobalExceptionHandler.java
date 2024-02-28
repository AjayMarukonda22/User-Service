package users.userservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import users.userservice.Dtos.ExceptionDto;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException) {
       return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionDto> handleUserAlreadyExistException(UserAlreadyExistException userAlreadyExistException) {
       return new ResponseEntity<>(new ExceptionDto(HttpStatus.ALREADY_REPORTED, userAlreadyExistException.getMessage()), HttpStatus.ALREADY_REPORTED);
   }
}
