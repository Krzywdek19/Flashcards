package com.flashcards.app;

import com.flashcards.app.users.User;
import com.flashcards.app.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!userRepository.existsByUsername("krzywdek19@wp.pl")) {
			userRepository.save(User.builder()
					.username("krzywdek19@wp.pl")
					.password("xyz123")
					.build());
		}
	}
}
