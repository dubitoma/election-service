package com.voting.president.dto;

/**
 * Data Transfer Object to return a error response.
 *
 */
public class ErrorDto {
    private String errorMessage;

    /**
     * Constructor to fully initialise the ErrorDto as response
     *
     * @param errorMessage reason of what went wrong
     */
    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
