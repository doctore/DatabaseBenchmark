package com.database.benchmark.jdbc.model.mapper;

import com.database.benchmark.jdbc.model.Pizza;
import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static com.database.benchmark.jdbc.model.mapper.IngredientMapper.ingredientRowMapper;

@UtilityClass
public class PizzaMapper {

    public static final RowMapper<Pizza> pizzaRowMapper = (resultSet, rowNum) ->
            new Pizza(
                    resultSet.getInt("pizza_id"),
                    resultSet.getString("pizza_name"),
                    resultSet.getDouble("pizza_cost"),
                    new LinkedHashSet<>()
            );

    public static final ResultSetExtractor<List<Pizza>> pizzaWithIngredientsResultExtractor = (resultSet) -> {
        List<Pizza> result = new ArrayList<>();
        Pizza pizza = null;
        int previousPizzaId = 0;
        while (resultSet.next()) {
            int currentPizzaId = resultSet.getInt("pizza_id");
            if (previousPizzaId != currentPizzaId) {
                pizza = pizzaRowMapper.mapRow(resultSet, 1);
                result.add(pizza);
                previousPizzaId = currentPizzaId;
            }
            pizza.addIngredient(ingredientRowMapper.mapRow(resultSet, 1));
        }
        return result;
    };

}
