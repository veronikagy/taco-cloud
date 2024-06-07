package tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import tacos.entities.TacoOrder;

public class JmsOrderMessagingService implements OrderMessagingService {

  private JmsTemplate jms;

  public JmsOrderMessagingService(JmsTemplate jms) {
    this.jms = jms;
  }

  @Override
  public void sendOrder(TacoOrder order) {
    jms.convertAndSend("tacocloud.order.queue", order,
        message -> {
          message.setStringProperty("X_ORDER_SOURCE", "WEB");
          return message;
        });
  }
}
