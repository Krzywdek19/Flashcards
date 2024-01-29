package com.flashcards.app.flashcardSets.dto;

import com.flashcards.app.flashcards.Flashcard;
import com.flashcards.app.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardSetDTO {
    private String name;
    private Set<User> users = new HashSet<>();
    private Set<Flashcard> flashcards = new HashSet<>();
}
