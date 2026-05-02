package com.portfolio.config;

import com.portfolio.model.User;
import com.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // 🔍 check if user already exists
        if (userRepo.findByEmail("pramanikshirsendu6@gmail.com").isEmpty()) {

            User user = new User();
            user.setName("Shirsendu");
            user.setEmail("pramanikshirsendu6@gmail.com");

            // 🔐 always encode password
            user.setPassword(passwordEncoder.encode("Sourav@09"));

            userRepo.save(user);

            System.out.println("✅ Default user created: pramanikshirsendu6@gmail.com");
        }
    }
}
