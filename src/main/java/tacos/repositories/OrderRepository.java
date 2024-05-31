package tacos.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import tacos.model.TacoOrder;
public interface OrderRepository
    extends CrudRepository<TacoOrder, UUID> {
}