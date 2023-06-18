package com.f1dot5.order;

import com.f1dot5.data.Customer;
import com.f1dot5.data.CustomerDelivery;
import com.f1dot5.data.SalesInvoice;
import com.f1dot5.data.repository.CustomerDeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes({"salesInvoice", "customer"})
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
            @ModelAttribute SalesInvoice salesInvoice,
            @ModelAttribute Customer customer,
            HttpServletRequest request
    ) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        customerDelivery.setCustomer(customer.getName());
        customerDelivery.setSalesInvoice(salesInvoice);

        log.info("Processing order: {}", customerDelivery);

        CustomerDelivery delivery = customerDeliveryRepo.save(customerDelivery);

        if (delivery == null) {
            log.info("Can't save order: {}", customerDelivery);
            return "orderForm";
        }

        request.getSession().removeAttribute("salesInvoice");

        return "redirect:/dashboard";
    }
}