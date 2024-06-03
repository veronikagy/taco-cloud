package tacos.repositories;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.entities.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

  Taco save(Taco taco1);

  Optional<Taco> findById(Long id);
}
