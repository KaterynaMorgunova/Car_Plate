package com.f1dot5.order;

import com.f1dot5.auth.LoginUser;
import com.f1dot5.data.CartOrder;
import com.f1dot5.data.Customer;
import com.f1dot5.data.CustomerDelivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes({"cartOrder", "loggedUser"})
public class OrderController {

    @ModelAttribute(name = "customerDelivery")
    public CustomerDelivery customerDelivery() {
        return new CustomerDelivery();
    }

    @ModelAttribute(name = "customer")
    public Customer customer() {
        return new Customer();
    }

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid  @ModelAttribute CustomerDelivery customerDelivery,
            Errors errors,
            @ModelAttribute LoginUser loggedUser,
            @ModelAttribute CartOrder cartOrder,
            SessionStatus sessionStatus
    ) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        customerDelivery.setCartOrder(cartOrder);

        log.info("Processing order: {}", customerDelivery);
        sessionStatus.setComplete();

        return "redirect:/dashboard";
    }
}