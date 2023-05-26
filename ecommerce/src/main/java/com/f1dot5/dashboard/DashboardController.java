package com.f1dot5.dashboard;

import com.f1dot5.auth.LoginUser;
import com.f1dot5.data.CartArticle;
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
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"cartOrder", "customer"})
public class DashboardController {
    private final DashboardManager dashboardManager;

    @Autowired
    public DashboardController(
            DashboardManager dashboardManager) {
        this.dashboardManager = dashboardManager;
    }

    @ModelAttribute(name = "dashboard")
    public Dashboard dashboard() {
        Dashboard result = new Dashboard();
        result.setDashboardArticles(dashboardManager.dashboardArticles());
        return result;
    }

    @ModelAttribute(name = "cartOrder")
    public CartOrder cartOrder() {
        return new CartOrder();
    }

    @GetMapping
    public String showDashboardForm(@ModelAttribute Customer customer) {
        return "dashboard";
    }

    @PostMapping
    public String processDashboard(
            @Valid @ModelAttribute Dashboard dashboard,
            Errors errors,
            @ModelAttribute CartOrder cartOrder
            ) {

        dashboard.setError(null);
        if (errors.hasErrors()) {
            return "dashboard";
        }

        List<DashboardArticle> desiredArticles = dashboard.desiredArticles();
        if (desiredArticles.isEmpty()) {
            dashboard.setError("Your cart is empty");
            return "dashboard";
        }

        List<CartArticle> cartArticles = desiredArticles.stream()
                .map(x -> x.getCartArticle())
                .collect(Collectors.toList());

        cartOrder.setCartArticles(cartArticles);
        cartOrder.setCurrency(desiredArticles.stream().findFirst().get().getArticle().getCurrency());
        log.info("Processing dashboard: {}", dashboard);

        return "redirect:/order/current";
    }
}
