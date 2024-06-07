package tacos.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import tacos.entities.TacoOrder;
import tacos.messaging.OrderMessagingService;

public class KafkaOrderMessagingService implements OrderMessagingService {

  private KafkaTemplate<String, TacoOrder> kafkaTemplate;

  public KafkaOrderMessagingService() {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void sendOrder(TacoOrder order) {
    kafkaTemplate.send("tacocloud.orders.topic", order);
  }
}
