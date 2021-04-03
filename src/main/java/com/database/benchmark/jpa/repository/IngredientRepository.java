package com.database.benchmark.jpa.repository;

import com.database.benchmark.jpa.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ingredientRepositoryJPA")
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
