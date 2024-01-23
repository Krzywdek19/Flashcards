package com.flashcards.app.flashcardSets;

import com.flashcards.app.flashcards.Flashcard;
import com.flashcards.app.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flashcard_sets")
@Getter
@Setter
@NoArgsConstructor
public class FlashcardSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "set", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Flashcard> flashcards = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "flashcard_sets_users",
            joinColumns = @JoinColumn(name = "flashcard_set_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final Set<User> users = new HashSet<>();

    public FlashcardSet(String name) {
        this.name = name;
    }
}
