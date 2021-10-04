package com.voting.president.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Election service exception handler advice
 */
@ControllerAdvice
public class ElectionsServiceErrorAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ElectionsServiceErrorAdvice.class);

    /**
     * Exception handler if NoSuchElementException is thrown
     *
     * @param e exception
     * @return  error ResponseEntity
     */
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorDto> handleNoSuchElementException(NoSuchElementException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    /**
     * Exception handler if IllegalArgumentException is thrown
     *
     * @param e exception
     * @return  error ResponseEntity
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorDto>  handleIllegalArgumentException(IllegalArgumentException e) {
        return error(HttpStatus.FORBIDDEN, e);
    }

    /**
     * Exception handler if IllegalStateException is thrown
     *
     * @param e exception
     * @return  error ResponseEntity
     */
    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<ErrorDto>  handleIllegalArgumentException(IllegalStateException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    /**
     * Return error as response
     *
     * @param status HTTP status code on error
     * @param e exception
     * @return ResponseEntity with HTTP status and ErrorDto
     */
    private ResponseEntity<ErrorDto> error(HttpStatus status, Exception e) {
        logger.error("Exception : ", e);
        return ResponseEntity.status(status).body(new ErrorDto(e.getMessage()));
    }
}
