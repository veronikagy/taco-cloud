package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import tacos.entities.Ingredient;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

  public static void main(String[] args) {
    SpringApplication.run(RestExamples.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public CommandLineRunner putAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      log.info("----------------------- PUT -------------------------");
      Ingredient before = tacoCloudClient.getIngredientById("LETC");
      log.info("BEFORE:  " + before);
      tacoCloudClient.updateIngredient(new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
      Ingredient after = tacoCloudClient.getIngredientById("LETC");
      log.info("AFTER:  " + after);
    };
  }
}
