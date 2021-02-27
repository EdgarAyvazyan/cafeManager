package com.sfl.cafemanager.rest.controller;

import com.sfl.cafemanager.rest.model.User;
import com.sfl.cafemanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param user to persist in database
     * @return created user with id.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole(T(com.sfl.cafemanager.enums.Role).ROLE_ADMIN, T(com.sfl.cafemanager.enums.Role).ROLE_MANAGER)")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * @return All users from DB.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}