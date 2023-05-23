package com.f1dot5.auth;

import com.f1dot5.data.CartOrder;
import com.f1dot5.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/login")
@SessionAttributes({"loggedUser"})
public class LoginController {
    private final CustomerRepository customerRepo;

    @Autowired
    public LoginController(
            CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }
    @ModelAttribute(name = "loggedUser")
    public LoginUser user() {
        return new LoginUser();
    }
    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @PostMapping
    public String login(
            @ModelAttribute(name = "loggedUser") @Valid LoginUser user,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            log.info("Processing user: {}", user);
            return "login";
        }

        return "redirect:/dashboard";
    }
}
