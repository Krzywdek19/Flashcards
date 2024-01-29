package com.flashcards.app.flashcards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flashcards.app.flashcardSets.FlashcardSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String frontSide;
    @NotEmpty
    private String backSide;

    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "flashcard_set_id")
    private FlashcardSet set;
}
