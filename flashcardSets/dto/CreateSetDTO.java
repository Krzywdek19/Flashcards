package com.flashcards.app.flashcardSets.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreateSetDTO(@NotEmpty String email, @NotEmpty String flashcardSetName) {
}
