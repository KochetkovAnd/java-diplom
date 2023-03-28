package ru.scratch.exception;

public class InvalidAuthenticationInformationException extends AbstractException {

    public InvalidAuthenticationInformationException(String message, String techInfo) {
        super(message, techInfo);
    }
}
