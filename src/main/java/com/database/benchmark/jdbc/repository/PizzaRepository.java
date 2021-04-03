package com.database.benchmark.jdbc.repository;

import com.database.benchmark.jdbc.model.Pizza;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.database.benchmark.jdbc.model.mapper.PizzaMapper.pizzaWithIngredientsResultExtractor;
import static com.database.benchmark.jdbc.repository.IngredientRepository.INGREDIENT_ALIAS;
import static com.database.benchmark.jdbc.repository.IngredientRepository.INGREDIENT_SELECT;
import static com.database.benchmark.jdbc.repository.IngredientRepository.INGREDIENT_TABLE;

@AllArgsConstructor
@Repository(value = "pizzaRepositoryJDBC")
public class PizzaRepository {

    public static final String PIZZA_TABLE = "pizza";
    public static final String PIZZA_SELECT = PIZZA_TABLE + ".id as pizza_id, "
                                            + PIZZA_TABLE + ".name as pizza_name, "
                                            + PIZZA_TABLE + ".cost as pizza_cost ";
    public static final String PIZZA_ALIAS = PIZZA_TABLE + " " + PIZZA_TABLE + " ";
    public static final String PIZZA_ORDER_BY_ID = "order by " + PIZZA_TABLE + ".id ";
    public static final String PIZZA_JOIN_INGREDIENTS_MANY_TO_MANY = PIZZA_TABLE + "_" + INGREDIENT_TABLE + "pi "
                                                                   + " ON pi.pizza_id = " + PIZZA_TABLE + ".id ";
    public static final String INGREDIENTS_JOIN_MANY_TO_MANY = INGREDIENT_ALIAS
                                                             + " ON " + INGREDIENT_TABLE + ".id = pi.ingredient_id ";

    @Lazy
    private final NamedParameterJdbcTemplate jdbcTemplate;


    public List<Pizza> findAllWithIngredients() {
        return jdbcTemplate.query(
                buildQueryWithIngredients()
                  + PIZZA_ORDER_BY_ID,
                pizzaWithIngredientsResultExtractor
        );
    }


    private String buildQueryWithIngredients() {
        return "select " + PIZZA_SELECT
             + "      ," + INGREDIENT_SELECT
             + "from " + PIZZA_ALIAS
             + "left join " + PIZZA_JOIN_INGREDIENTS_MANY_TO_MANY
             + "left join " + INGREDIENTS_JOIN_MANY_TO_MANY;
    }

}
