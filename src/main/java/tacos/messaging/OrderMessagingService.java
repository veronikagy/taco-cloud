package tacos.messaging;

import tacos.entities.TacoOrder;

public interface OrderMessagingService {
  void sendOrder(TacoOrder order);

}