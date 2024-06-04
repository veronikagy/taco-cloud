package tacos.restclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.entities.Ingredient;
@Service
public class TacoCloudClient {
  private RestTemplate rest;

  public TacoCloudClient(RestTemplate rest) {
    this.rest = rest;
  }

  public Ingredient getIngredientById(String ingredientId){
    return rest.getForObject("http://localhost:8080/ingredients/{id}",
        Ingredient.class, ingredientId);
  }
  public void updateIngredient(Ingredient ingredient){
    rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
  }
  public void deleteIngredient(Ingredient ingredient){
    rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
  }
  public Ingredient createIngredient(Ingredient ingredient) {
    return rest.postForObject("http://localhost:8080/ingredients/{id}", ingredient,
        Ingredient.class);
  }

}
