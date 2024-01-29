package com.flashcards.app.flashcardSets;

import com.flashcards.app.flashcardSets.dto.CreateSetDTO;
import com.flashcards.app.flashcardSets.exceptions.FlashcardSetDoesNotFoundException;
import com.flashcards.app.users.UserRepository;
import com.flashcards.app.users.exceptions.UserDoesNotExistException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/API/v1/sets")
@Validated
@RequiredArgsConstructor
public class FlashcardSetController {
    private final UserRepository userRepository;
    private final FlashcardSetRepository flashcardSetRepository;

    private final FlashcardSetService flashcardSetService;


    @PostMapping
    public ResponseEntity<?> createSet(@RequestBody @Valid CreateSetDTO createSetDTO) throws UserDoesNotExistException {
        var user = userRepository.findByUsername(createSetDTO.email()).orElseThrow(() -> new UserDoesNotExistException("User with this email doesn't exist"));
        this.flashcardSetRepository.save(this.flashcardSetService.createFlashcardSet(user, createSetDTO.flashcardSetName()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSet(@PathVariable Integer id){
        if(!flashcardSetRepository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        flashcardSetRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateSet(@PathVariable Integer id,@RequestBody String name){
        var flashcardSet = this.flashcardSetRepository.findById(id).orElseThrow(() -> new FlashcardSetDoesNotFoundException("FlashcardSet with this ID doesn't exist"));
        flashcardSet.setName(name);
        this.flashcardSetRepository.save(flashcardSet);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readSet(@PathVariable Integer id){
        return ResponseEntity.ok(this.flashcardSetRepository.findById(id).orElseThrow(() -> new FlashcardSetDoesNotFoundException("FlashcardSet with this ID doesn't exist")));
    }

    @GetMapping
    public ResponseEntity<?> readAllSets(){
        return ResponseEntity.ok(this.flashcardSetRepository.findAll());
    }

    @ExceptionHandler(FlashcardSetDoesNotFoundException.class)
    public ResponseEntity<?> handleFlashcardSetDoesNotFound(){
        return ResponseEntity.ok("Flashcard with this ID doesn't exist");
    }
}
