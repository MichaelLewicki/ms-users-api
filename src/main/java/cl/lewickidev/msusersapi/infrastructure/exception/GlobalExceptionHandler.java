package cl.lewickidev.msusersapi.infrastructure.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        //ex.printStackTrace();
        log.info("MethodArgumentNotValidException: {}", ex.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HandlerMethodValidationException.class })
    public ResponseEntity<?> handleMethodValidationException(HandlerMethodValidationException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage().replace("\\", "").replace("\"", ""));
        //ex.printStackTrace();
        log.info("HandlerMethodValidationException: {}", ex.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        ex.printStackTrace();
        log.error("ConstraintViolationException: {}", ex.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { TransactionSystemException.class })
    public ResponseEntity<?> handleTransactionSystemException(TransactionSystemException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        ex.printStackTrace();
        log.error("TransactionSystemException: {}", ex.toString());
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof ConstraintViolationException) {
            body.clear();
            ConstraintViolationException constraintViolationEx = (ConstraintViolationException) rootCause;
            List<String> violations = new ArrayList<>();
            for (ConstraintViolation<?> violation : constraintViolationEx.getConstraintViolations()) {
                violations.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            String errorMessage = "Validation failed: " + String.join(", ", violations);
            body.put("mensaje", errorMessage);
            log.error("ConstraintViolationException from TransactionSystemException : {}", errorMessage);
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HandledException.class })
    public ResponseEntity<?> handleHandledException(HandledException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.parseInt(ex.getCode()));
        body.put("mensaje", ex.getMessage());
        //ex.printStackTrace();
        log.error("HandledException: {}", ex.toString());
        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<?> handleConstraintViolationException(HttpMessageNotReadableException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        //ex.printStackTrace();
        log.error("HttpMessageNotReadableException: {}", ex.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MissingRequestHeaderException.class })
    public ResponseEntity<?> missingRequestHeaderException(MissingRequestHeaderException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        //ex.printStackTrace();
        log.error("MissingRequestHeaderException: {}", ex.toString());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        ex.printStackTrace();
        log.error("Exception: {}", ex.toString());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

