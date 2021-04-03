package com.database.benchmark.jpa.repository;

import com.database.benchmark.jpa.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "pizzaRepositoryJPA")
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    /*
    @Query("SELECT DISTINCT p FROM Pizza p LEFT JOIN FETCH p.ingredients ORDER BY p.id")
    List<Pizza> findAllWithIngredients();
     */

}
