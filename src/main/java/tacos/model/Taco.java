package tacos.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Taco {

  @Id
  private Long id;
  private Date createdAt = new Date();
  @NotNull
  @Size(min = 4, message = "Name must be at least 4 characters long")
  private String name;
  @NotNull
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;
}
