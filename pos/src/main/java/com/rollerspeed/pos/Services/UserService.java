package com.rollerspeed.pos.Services;

import java.util.List;

//import org.springframework.stereotype.Service;

import java.util.Optional;

import com.rollerspeed.pos.Model.User;

import org.springframework.security.core.userdetails.UserDetails;

//@Service

public interface UserService {
    User saveUser(User user);

    Optional<User> findUserById(Long id);

    List<User> findAllUsers();

    Optional<User> findUserByUsername(String username);

    boolean usernameExists(String username);

    void deleteUserById(Long id);

    long countUsers();

    UserDetails loadUserByUsername(String username);
    
}
