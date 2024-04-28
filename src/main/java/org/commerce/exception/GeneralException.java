package org.commerce.exception;

import lombok.Getter;

@Getter
public class GeneralException extends Exception{

    private final String statusCode;
    private final String generalMessage;

    public GeneralException(String statusCode, String message, String generalMessage) {
        super(message);
        this.statusCode = statusCode;
        this.generalMessage = generalMessage;
    }

    public GeneralException(String message, Throwable cause, String statusCode, String generalMessage) {
        super(message, cause);
        this.statusCode = statusCode;
        this.generalMessage = generalMessage;
    }
}