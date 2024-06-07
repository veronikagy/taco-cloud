package tacos.messaging.jms;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.entities.TacoOrder;
import tacos.messaging.OrderMessagingService;
import tacos.repositories.OrderRepository;

@RestController
@RequestMapping(path="/api/orders",
    produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class OrderApiController {

  private OrderRepository repo;
  private OrderMessagingService messageService;

  public OrderApiController(
      OrderRepository repo,
      OrderMessagingService messageService) {
    this.repo = repo;
    this.messageService = messageService;
  }
  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TacoOrder postOrder(@RequestBody TacoOrder order) {
    messageService.sendOrder(order);
    return repo.save(order);
  }
}