package com.database.benchmark.jdbc.model.mapper;

import com.database.benchmark.jdbc.model.Ingredient;
import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.RowMapper;

@UtilityClass
public class IngredientMapper {

    public static final RowMapper<Ingredient> ingredientRowMapper = (resultSet, rowNum) ->
            new Ingredient(
                    resultSet.getInt("ingredient_id"),
                    resultSet.getString("ingredient_name")
            );

}
