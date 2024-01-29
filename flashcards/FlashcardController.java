package com.flashcards.app.flashcards;

import com.flashcards.app.flashcardSets.FlashcardSetRepository;
import com.flashcards.app.flashcards.dto.FlashcardDTO;
import com.flashcards.app.flashcards.dto.FlashcardPutDTO;
import com.flashcards.app.flashcards.exceptions.FlashcardDoesNotFoundException;
import com.flashcards.app.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/API/v1/flashcards")
public class FlashcardController {
    private final FlashcardSetRepository flashcardSetRepository;
    private final UserRepository userRepository;
    private final FlashcardRepository flashcardRepository;
    private final FlashcardService flashcardService;
    @PostMapping("/{setId}")
    public ResponseEntity<?> addFlashcardToSet(@PathVariable Integer setId, @RequestBody FlashcardDTO flashcardDTO){
        return ResponseEntity.ok(flashcardService.addFlashcardToSet(flashcardDTO,setId));
    }

    @DeleteMapping("/{flashcardId}")
    public ResponseEntity<?> deleteFlashcard(@PathVariable Integer flashcardId){
        return ResponseEntity.ok(this.flashcardService.removeFlashcardFromSet(flashcardId));
    }

    @PutMapping("/{flashcardId}")
    public ResponseEntity<?> updateFlashcard(@PathVariable Integer flashcardId, @RequestBody FlashcardPutDTO flashcardPutDTO){
        return ResponseEntity.ok(this.flashcardService.updateFlashcard(flashcardId, flashcardPutDTO));
    }

    @GetMapping("/{flashcardId}")
    public ResponseEntity<?> getFlashcardById(@PathVariable Integer flashcardId){
        return ResponseEntity.ok(this.flashcardRepository.findById(flashcardId).orElseThrow(()-> new FlashcardDoesNotFoundException("Flashcard with this ID doesn't exist")));
    }

    @GetMapping
    public ResponseEntity<?> getFlashcards(){
        return ResponseEntity.ok(this.flashcardRepository.findAll());
    }

    @ExceptionHandler(FlashcardDoesNotFoundException.class)
    public ResponseEntity<?> flashcardDoesNotFoundHandler(){
        return ResponseEntity.badRequest().build();
    }
}
