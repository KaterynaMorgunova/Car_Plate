package com.f1dot5.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@SessionAttributes("loggedUser")
public class DashboardController {

    @ModelAttribute
    public void addArticlesToModel(Model model) {
        List<DashboardArticle> availableArticles = DashboardArticleByIdConverter.ALL_ARTICLES;//.stream().filter(x -> x.getAvailableQuantity() > 0).toList();
        model.addAttribute("availableArticles", availableArticles);
    }

    @GetMapping
    public String showDashboardForm() {
        return "dashboard";
    }

    @ModelAttribute(name = "dashboard")
    public Dashboard dashboard() {
        return new Dashboard();
    }

    @PostMapping
    public String processDashboard(
            @ModelAttribute Dashboard dashboard, Errors errors) {

        if (errors.hasErrors()) {
            return "dashboard";
        }

//        dashboard.addTaco(taco);
        log.info("Processing dashboard: {}", dashboard);

        return "redirect:/order/current";
    }
}
