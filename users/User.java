package com.flashcards.app.users;

import com.flashcards.app.flashcardSets.FlashcardSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @ManyToMany
    private final Set<FlashcardSet> flashcardSets = new HashSet<>();
}
