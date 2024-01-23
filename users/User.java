package com.flashcards.app.users;

import com.flashcards.app.flashcardSets.FlashcardSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @ManyToMany
    Set<FlashcardSet> flashcardSets = new HashSet<>();
}
