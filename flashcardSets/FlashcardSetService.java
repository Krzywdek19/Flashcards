package com.flashcards.app.flashcardSets;

import com.flashcards.app.users.User;
import org.springframework.stereotype.Service;

@Service
public class FlashcardSetService {

    FlashcardSet createFlashcardSet(User user, String setName){
        var flashcardSet = new FlashcardSet(setName);
        flashcardSet.getUsers().add(user);
        return flashcardSet;
    }
}
