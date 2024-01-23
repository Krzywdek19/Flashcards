package com.flashcards.app.flashcardSets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardSetRepository extends JpaRepository<FlashcardSet, Integer> {
}
