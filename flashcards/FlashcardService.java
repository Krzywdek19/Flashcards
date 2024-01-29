package com.flashcards.app.flashcards;

import com.flashcards.app.flashcardSets.FlashcardSet;
import com.flashcards.app.flashcardSets.FlashcardSetRepository;
import com.flashcards.app.flashcardSets.exceptions.FlashcardSetDoesNotFoundException;
import com.flashcards.app.flashcards.dto.FlashcardDTO;
import com.flashcards.app.flashcards.dto.FlashcardPutDTO;
import com.flashcards.app.flashcards.exceptions.FlashcardDoesNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final FlashcardSetRepository flashcardSetRepository;

    FlashcardSet addFlashcardToSet(FlashcardDTO flashcardDTO, Integer setId){
        var flashcardSet = flashcardSetRepository.findById(setId).orElseThrow(() -> new FlashcardSetDoesNotFoundException("Flashcard set with this ID doesn't exist"));
        flashcardSet.getFlashcards().add(Flashcard.builder().set(flashcardSet).backSide(flashcardDTO.backSide()).frontSide(flashcardDTO.frontSide()).build());
        flashcardSetRepository.save(flashcardSet);
        return flashcardSet;
    }

    FlashcardSet removeFlashcardFromSet(Integer flashcardId){
        var flashcard = this.flashcardRepository.findById(flashcardId).orElseThrow(() -> new FlashcardDoesNotFoundException("Flashcard with this ID doesn't exist"));
        this.flashcardRepository.delete(flashcard);
        return flashcard.getSet();
    }

    Flashcard updateFlashcard(Integer flashcardId, FlashcardPutDTO flashcardDTO){
        var flashcard = this.flashcardRepository.findById(flashcardId).orElseThrow(() -> new FlashcardDoesNotFoundException("Flashcard with this ID doesn't exist"));
        if(!flashcard.getFrontSide().equals(flashcardDTO.getFrontSide()) && flashcardDTO.getFrontSide() != null){
            flashcard.setFrontSide(flashcardDTO.getFrontSide());
        }
        if(!flashcard.getBackSide().equals(flashcardDTO.getBackSide()) && flashcardDTO.getBackSide() != null){
            flashcard.setBackSide(flashcardDTO.getBackSide());
        }
        return this.flashcardRepository.save(flashcard);
    }
}
