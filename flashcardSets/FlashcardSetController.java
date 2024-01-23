package com.flashcards.app.flashcardSets;

import com.flashcards.app.users.UserRepository;
import com.flashcards.app.users.exceptions.UserDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/API/v1/sets")
public class FlashcardSetController {
    private final UserRepository userRepository;
    private final FlashcardSetRepository flashcardSetRepository;

    private final FlashcardSetService flashcardSetService;

    public FlashcardSetController(UserRepository userRepository, FlashcardSetRepository flashcardSetRepository, FlashcardSetService flashcardSetService) {
        this.userRepository = userRepository;
        this.flashcardSetRepository = flashcardSetRepository;
        this.flashcardSetService = flashcardSetService;
    }

    @PostMapping
    public ResponseEntity<?> createSet(@RequestBody Map<String, String> body) throws UserDoesNotExistException {
        var user = userRepository.findByUsername(body.get("email")).orElseThrow(() -> new UserDoesNotExistException("User with this email doesn't exist"));
        this.flashcardSetRepository.save(this.flashcardSetService.createFlashcardSet(user, body.get("setName")));
        return ResponseEntity.ok().build();
    }
}
