package com.flashcards.app.flashcards;

import com.flashcards.app.flashcardSets.FlashcardSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
@NoArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String frontSide;
    @NotEmpty
    private String backSide;

    @NotEmpty
    @ManyToOne
    private FlashcardSet set;
}
