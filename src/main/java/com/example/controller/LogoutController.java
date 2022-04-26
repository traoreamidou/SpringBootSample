package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LogoutController {
    
    @PostMapping("/logout")
    public String postLogout() {
    	log.info("Logout");
        return "redirect:/login";
    }
}
