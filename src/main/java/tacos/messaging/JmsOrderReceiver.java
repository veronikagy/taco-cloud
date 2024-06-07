package tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.entities.TacoOrder;

@Component
public class JmsOrderReceiver implements OrderReceiver {

  private JmsTemplate jms;

  public JmsOrderReceiver(JmsTemplate jms) {
    this.jms = jms;
  }

  @Override
  public TacoOrder receiveOrder() {
    return (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");

  }

}
