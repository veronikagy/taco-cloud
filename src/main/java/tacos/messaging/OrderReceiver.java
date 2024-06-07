package tacos.messaging;

import jakarta.jms.JMSException;
import tacos.entities.TacoOrder;

public interface OrderReceiver {

  TacoOrder receiveOrder() throws JMSException;
}
