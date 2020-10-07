package com.finartz.technicaltask.flightagency.advice;

import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreError;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreException;
import com.finartz.technicaltask.flightagency.util.MessageSourceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class MainControllerAdvice {

    @ExceptionHandler(FlightAgencyCoreException.class)
    public final ResponseEntity<FlightAgencyCoreException> handleServiceException(
            FlightAgencyCoreException ex, WebRequest request) {
        return prepareResponse(ex);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<FlightAgencyCoreException> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        MethodParameter parameter = ex.getParameter();
        String parameterType = parameter.getParameterType().getName();
        String parameterName = ex.getName();

        FlightAgencyCoreException exception = new FlightAgencyCoreException(
                FlightAgencyCoreError.METHOD_ARGUMENT_TYPE_MISMATCH, ex.getRootCause(), parameterType, parameterName);

        return prepareResponse(exception);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<FlightAgencyCoreException> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        return prepareResponse(new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_ALREADY_EXISTS, ex));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<FlightAgencyCoreException> handleNoSuchElementException(
            NoSuchElementException ex, WebRequest request) {

        return prepareResponse(new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_NO_VALUE, ex,
                ex.getMessage()));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<FlightAgencyCoreException> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException ex, WebRequest request) {
        return prepareResponse(new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_NO_VALUE,
                ex.getRootCause(), ex.getMessage()));
    }

    private static ResponseEntity<FlightAgencyCoreException> prepareResponse(FlightAgencyCoreException exception) {
        FlightAgencyCoreException ex = MessageSourceUtil.prepareException(exception);
        log.error("Controller-exception {}", kv("exception", ex));
        return ResponseEntity.status(exception.getError().getHttpStatus()).body(ex);
    }

}
