package com.database.benchmark.r2dbc.repository;

import com.database.benchmark.r2dbc.model.Pizza;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "pizzaRepositoryR2DBC")
public interface PizzaRepository extends R2dbcRepository<Pizza, Integer> {

    String PIZZA_TABLE = "pizza";
    String PIZZA_SELECT = PIZZA_TABLE + ".id as pizza_id, "
                        + PIZZA_TABLE + ".name as pizza_name, "
                        + PIZZA_TABLE + ".cost as pizza_cost ";
    String PIZZA_ALIAS = PIZZA_TABLE + " " + PIZZA_TABLE + " ";

}
