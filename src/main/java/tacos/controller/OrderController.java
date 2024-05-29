package tacos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.model.TacoOrder;
import tacos.repositories.OrderRepository;

@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
public class OrderController {

  private OrderRepository orderRepo;
  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
  }
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  @PostMapping()
  public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    orderRepo.save(order);
    log.info("Order submitted: {}", order);
    sessionStatus.setComplete();
    return "redirect:/";
  }
}
