package com.flashcards.app.flashcards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FlashcardPutDTO {
    private String frontSide;
    private String backSide;
}
