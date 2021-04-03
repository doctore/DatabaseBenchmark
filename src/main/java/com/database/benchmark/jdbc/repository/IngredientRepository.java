package com.database.benchmark.jdbc.repository;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository(value = "ingredientRepositoryJDBC")
public class IngredientRepository {

    public static final String INGREDIENT_TABLE = "ingredient";
    public static final String INGREDIENT_SELECT = INGREDIENT_TABLE + ".id as ingredient_id, "
                                                 + INGREDIENT_TABLE + ".name as ingredient_name ";
    public static final String INGREDIENT_ALIAS = INGREDIENT_TABLE + " " + INGREDIENT_TABLE + " ";

    @Lazy
    private final NamedParameterJdbcTemplate jdbcTemplate;

}
