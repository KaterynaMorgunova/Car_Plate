package com.f1dot5.auth;

import com.f1dot5.data.Customer;
import com.f1dot5.data.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/signup")
@SessionAttributes("customer")
public class SignupController {
    private final CustomerRepository customerRepo;

    @Autowired
    public SignupController(
            CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @ModelAttribute(name = "loggedUser")
    public SignupUser user() {
        return new SignupUser();
    }
    @ModelAttribute(name = "customer")
    public Customer customer() {
        return new Customer();
    }

    @GetMapping
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping
    public String signup(
            @ModelAttribute(name = "loggedUser") @Valid SignupUser user,
            Errors errors,
            @ModelAttribute(name = "customer") Customer customer
    ) {

        if (errors.hasErrors()) {
            log.info("Processing user: {}", user);
            return "signup";
        }

        if (this.customerRepo.findByNameAndPassword(user.getName(), user.getPassword()) != null) {
            user.setAuthenticationError("User with this name already registered");
            return "signup";
        }
        Customer dbCustomer = customerRepo.save(
                new Customer(
                        new Date(),
                        user.getName(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getPhone())
        );

        if (dbCustomer == null) {
            user.setAuthenticationError("Can't create user");
            return "signup";
        }

        return "redirect:/dashboard";
    }
}