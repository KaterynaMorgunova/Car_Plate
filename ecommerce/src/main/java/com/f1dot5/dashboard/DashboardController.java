package com.f1dot5.dashboard;

import com.f1dot5.data.CartArticle;
import com.f1dot5.data.SalesInvoice;
import com.f1dot5.data.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"salesInvoice", "customer"})
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

    @ModelAttribute(name = "salesInvoice")
    public SalesInvoice salesInvoice() {
        return new SalesInvoice();
    }

    @GetMapping
    public String showDashboardForm(@ModelAttribute Customer customer) {
        return "dashboard";
    }

    @PostMapping
    public String processDashboard(
            @Valid @ModelAttribute Dashboard dashboard,
            Errors errors,
            @ModelAttribute SalesInvoice salesInvoice
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

        float total = desiredArticles
                .stream()
                .map(x -> x.getCartArticle().getQuantity()
                        * x.getArticle().getPrice())
                .reduce(0.0f, (subtotal, x) -> subtotal
                        + x);

        salesInvoice.setCartArticles(cartArticles);
        salesInvoice.setTotalPrice(total);
        salesInvoice.setCurrency(desiredArticles.stream().findFirst().get().getArticle().getCurrency());
        log.info("Processing dashboard: {}", dashboard);

        return "redirect:/order/current";
    }
}
