package com.f1dot5.order;

import com.f1dot5.data.CartOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes({"cartOrder", "loggedUser"})
public class OrderController {

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping("/current")
    public String processOrder(
            @ModelAttribute CartOrder order, Errors errors) {

        if (errors.hasErrors()) {
            return "order/current";
        }

        log.info("Processing order: {}", order);

        return "redirect:/dashboard";
    }
}