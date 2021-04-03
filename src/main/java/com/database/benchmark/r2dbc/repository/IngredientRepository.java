package com.database.benchmark.r2dbc.repository;

import com.database.benchmark.r2dbc.model.Ingredient;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ingredientRepositoryR2DBC")
public interface IngredientRepository extends R2dbcRepository<Ingredient, Integer> {

    String INGREDIENT_TABLE = "ingredient";
    String INGREDIENT_SELECT = INGREDIENT_TABLE + ".id as ingredient_id, "
                             + INGREDIENT_TABLE + ".name as ingredient_name ";
    String INGREDIENT_ALIAS = INGREDIENT_TABLE + " " + INGREDIENT_TABLE + " ";

}
