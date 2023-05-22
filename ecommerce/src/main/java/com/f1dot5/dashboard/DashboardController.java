package com.f1dot5.dashboard;

import com.f1dot5.data.CartArticle;
import com.f1dot5.data.CartOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"cartOrder", "loggedUser"})
public class DashboardController {

    @ModelAttribute
    public void addArticlesToModel(Model model) {
        List<DashboardArticle> availableArticles = DashboardArticleByIdConverter.dashboardArticles();
        model.addAttribute("availableArticles", availableArticles);
    }

    @GetMapping
    public String showDashboardForm() {
        return "dashboard";
    }

    @ModelAttribute(name = "dashboard")
    public Dashboard dashboard() {
        Dashboard result = new Dashboard();
        result.setDashboardArticles(DashboardArticleByIdConverter.dashboardArticles());
        return result;
    }

    @ModelAttribute(name = "cartOrder")
    public CartOrder cartOrder() {
        return new CartOrder();
    }

    @PostMapping
    public String processDashboard(
            @ModelAttribute Dashboard dashboard,
            Errors errors,
            @ModelAttribute CartOrder cartOrder
            ) {

        if (errors.hasErrors()) {
            return "dashboard";
        }

        List<CartArticle> cartArticles = dashboard.getDashboardArticles().stream()
                .filter(x -> x.isChecked())
                .map(x -> x.getCartArticle())
                .collect(Collectors.toList());

        cartOrder.setCartArticles(cartArticles);
        log.info("Processing dashboard: {}", dashboard);

        return "redirect:/order/current";
    }
}
