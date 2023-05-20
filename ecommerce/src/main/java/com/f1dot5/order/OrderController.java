package com.f1dot5.order;

import com.f1dot5.dashboard.Dashboard;
import com.f1dot5.dashboard.DashboardArticle;
import com.f1dot5.dashboard.DashboardArticleByIdConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("dashboard")
public class OrderController {

    @ModelAttribute(name = "order")
    public Order order() {
        Order result =  new Order("1", 10, "USD");
        return result;
    }

    @GetMapping("/current")
    public String showOrderForm() {
        return "orderForm";
    }

    @PostMapping("/current")
    public String processOrder(
            @ModelAttribute Order order, Errors errors) {

        if (errors.hasErrors()) {
            return "order/current";
        }

        log.info("Processing order: {}", order);

        return "redirect:/dashboard";
    }
}