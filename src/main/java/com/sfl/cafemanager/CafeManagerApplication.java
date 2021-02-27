package com.sfl.cafemanager;

import com.sfl.cafemanager.enums.Role;
import com.sfl.cafemanager.rest.model.User;
import com.sfl.cafemanager.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class CafeManagerApplication implements CommandLineRunner {

    private final UserService userService;

    public CafeManagerApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CafeManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Optional<User> userById = userService.getUserById(1L);
        if (!userById.isPresent()) {
            User user = new User(
                   "Edgar",
                    "Ayvazyan",
                    "test@gmail.com",
                    "root",
                    "root",
                    Role.ROLE_ADMIN
            );
            userService.createUser(user);
        }
    }
}
