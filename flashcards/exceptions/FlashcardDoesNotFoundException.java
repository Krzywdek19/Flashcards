package com.flashcards.app.flashcards.exceptions;

public class FlashcardDoesNotFoundException extends Error{
    public FlashcardDoesNotFoundException(String message) {
        super(message);
    }
}
