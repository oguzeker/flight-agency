package com.finartz.technicaltask.flightagency.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlightAgencyCoreException extends RuntimeException {

    private FlightAgencyCoreError error = FlightAgencyCoreError.INTERNAL_SERVER_ERROR;
    private Object[] args;
    private String message;

    public FlightAgencyCoreException(FlightAgencyCoreError error, Object... args) {
        this.error = error;
        this.args = args;
    }

    public FlightAgencyCoreException(FlightAgencyCoreError error, Throwable cause, Object... args) {
        super(cause);
        this.error = error;
        this.args = args;
    }

}