package tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import tacos.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {


}