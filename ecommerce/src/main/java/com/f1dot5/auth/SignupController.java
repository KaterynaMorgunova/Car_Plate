package com.f1dot5.auth;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/signup")
@SessionAttributes("loggedUser")
public class SignupController {

    @ModelAttribute(name = "loggedUser")
    public SignupUser user() {
        return new SignupUser();
    }

    @GetMapping
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping
    public String signup(
            @Valid SignupUser user, Errors errors) {

        if (errors.hasErrors()) {
            log.info("Processing user: {}", user);
            return "signup";
        }

        return "redirect:/dashboard";
    }
}