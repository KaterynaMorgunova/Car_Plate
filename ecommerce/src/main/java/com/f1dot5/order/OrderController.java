package com.f1dot5.order;

import com.f1dot5.data.CartOrder;
import com.f1dot5.data.Customer;
import com.f1dot5.data.CustomerDelivery;
import com.f1dot5.data.repository.CustomerDeliveryRepository;
import com.f1dot5.data.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes({"cartOrder", "customer"})
public class OrderController {
    private final CustomerDeliveryRepository customerDeliveryRepo;

    @Autowired
    public OrderController(
            CustomerDeliveryRepository customerDeliveryRepo) {
        this.customerDeliveryRepo = customerDeliveryRepo;
    }

    @ModelAttribute(name = "customerDelivery")
    public CustomerDelivery customerDelivery() {
        return new CustomerDelivery();
    }

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(
            @Valid  @ModelAttribute CustomerDelivery customerDelivery,
            Errors errors,
            @ModelAttribute CartOrder cartOrder,
            @ModelAttribute Customer customer,
            HttpServletRequest request
    ) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        customerDelivery.setCustomer(customer.getName());
        customerDelivery.setCartOrder(cartOrder);

        log.info("Processing order: {}", customerDelivery);

        CustomerDelivery delivery = customerDeliveryRepo.save(customerDelivery);

        if (delivery == null) {
//            user.setAuthenticationError("Can't create user");
            return "orderForm";
        }

        request.getSession().removeAttribute("cartOrder");

        return "redirect:/dashboard";
    }
}