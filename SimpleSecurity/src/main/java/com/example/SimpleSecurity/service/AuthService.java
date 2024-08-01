package com.example.SimpleSecurity.service;

import com.example.SimpleSecurity.entity.User;
import com.example.SimpleSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class AuthService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public User verifyUser(User user){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        User found_user = userRepository.findByUsername(user.getUsername()).orElseThrow(
//                ()-> new UsernameNotFoundException("user not found"));
//        return found_user;
//    }
//}

