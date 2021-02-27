package com.sfl.cafemanager.service.impl;

import com.sfl.cafemanager.entity.OrderEntity;
import com.sfl.cafemanager.entity.TableEntity;
import com.sfl.cafemanager.entity.UserEntity;
import com.sfl.cafemanager.enums.Role;
import com.sfl.cafemanager.mapper.UserMapper;
import com.sfl.cafemanager.repository.OrderRepository;
import com.sfl.cafemanager.repository.TableRepository;
import com.sfl.cafemanager.repository.UserRepository;
import com.sfl.cafemanager.rest.model.User;
import com.sfl.cafemanager.configuration.security.jwt.domain.UserPrincipal;
import com.sfl.cafemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .map(userMapper::toDomain);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.toDomain(userRepository.findByUsername(username));
        return new UserPrincipal(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        if (Role.ROLE_WAITER.name().equals(userEntity.getRole())) {
            List<TableEntity> tables = tableRepository.findByWaiter(userEntity);
            tables.forEach(tableEntity -> {
                tableEntity.setWaiter(null);
                tableRepository.save(tableEntity);
            });

            List<OrderEntity> orders = orderRepository.findByWaiter(userEntity);
            orders.forEach(orderEntity -> {
                orderEntity.setWaiter(null);
                orderRepository.save(orderEntity);
            });
        }

        userRepository.deleteById(userId);
    }

}