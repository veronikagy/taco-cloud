package tacos.messaging.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tacos.entities.TacoOrder;
@Component
@Slf4j
public class KitchenUI {
  public void displayOrder(TacoOrder order){
    log.info("Order: " + order);
  }
}
