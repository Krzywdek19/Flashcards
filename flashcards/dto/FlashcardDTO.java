package com.flashcards.app.flashcards.dto;

import jakarta.validation.constraints.NotEmpty;


public record FlashcardDTO(@NotEmpty String frontSide,@NotEmpty String backSide) {
}
