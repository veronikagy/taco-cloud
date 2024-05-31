package tacos.model;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import tacos.model.udt.IngredientUDT;

@Data
@Table("tacos")
public class Taco {

  @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
  private UUID id = Uuids.timeBased();
  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;
  @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED,//ключи кластеризации используются для определения порядка хранения записей в разделе –
      // в данном случае в порядке убывания, поэтому в пределах данного раздела более
      // новые записи будут помещаться в начало таблицы tacos
      ordering = Ordering.DESCENDING)
  private Date createdAt = new Date();
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  @Column("ingredients")
  private List<IngredientUDT> ingredients = new ArrayList<>();

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
  }
}