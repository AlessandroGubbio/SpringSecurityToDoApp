package com.example.SimpleSecurity.controller;

import com.example.SimpleSecurity.entity.User;
import com.example.SimpleSecurity.repository.UserRepository;
import com.example.SimpleSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @GetMapping
    public String getLoginPage(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getPage(){
        log.info("login page retrieved");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, Model model) {
        log.info("post");
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword());
            authenticationManager.authenticate(authentication);
            log.info("login successful for user {}", user.getUsername());
            return "home";

        } catch (AuthenticationException e) {

            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User request){
        userService.save(request);
        return "home";
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(){
        return new ModelAndView("admin");
    }


}
