package project.board.crew.logic.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ApiException.class })
    protected ResponseEntity<Object> handleApiConflict(ApiException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {PropertyValueException.class})
    protected ResponseEntity<Object> handleValueConflict(PropertyValueException e, WebRequest request){
        return handleExceptionInternal(e, "One or more values are incorrect!", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintConflict(SQLIntegrityConstraintViolationException e, WebRequest request){
        return handleExceptionInternal(e, "Constraint violation! Make sure that unique values are unique!", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
