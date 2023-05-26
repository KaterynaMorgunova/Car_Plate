package com.f1dot5.auth;

import com.f1dot5.data.CartOrder;
import com.f1dot5.data.Customer;
import com.f1dot5.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/login")
@SessionAttributes({"customer"})
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
    @ModelAttribute(name = "customer")
    public Customer customer() {
        return new Customer();
    }
    @GetMapping
    public String showLoginForm() {
        return "login";
    }

    @PostMapping
    public String login(
            @ModelAttribute(name = "loggedUser") @Valid LoginUser user,
            Errors errors,
            @ModelAttribute(name = "customer") Customer customer
    ) {
        user.setAuthenticationError(null);
        if (errors.hasErrors()) {
            log.info("Processing user: {}", user);
            return "login";
        }

        Customer dbCustomer = customerRepo.findByCredentials(user.getName(), user.getPassword());
        if (dbCustomer == null) {
            user.setAuthenticationError("Authentication error");
            return "login";
        }

        customer.setName(dbCustomer.getName());
        customer.setEmail(dbCustomer.getEmail());
        customer.setPhone(dbCustomer.getPhone());

        return "redirect:/dashboard";
    }
}
