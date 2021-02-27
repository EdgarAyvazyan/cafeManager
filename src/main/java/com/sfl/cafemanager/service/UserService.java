package com.sfl.cafemanager.service;

import com.sfl.cafemanager.rest.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void deleteUser(Long userId);
}
