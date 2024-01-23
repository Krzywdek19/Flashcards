package com.flashcards.app.users.exceptions;

public class UserDoesNotExistException extends Exception{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
