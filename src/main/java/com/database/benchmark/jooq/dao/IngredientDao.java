package com.database.benchmark.jooq.dao;

import com.database.benchmark.jooq.model.Ingredient;
import com.database.benchmark.jooq.model.internal.tables.IngredientTable;
import com.database.benchmark.jooq.model.internal.tables.records.IngredientRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class IngredientDao extends ParentDao<IngredientRecord, Ingredient, Short> {


    /**
     * Create a new PizzaDao with an attached configuration
     */
    @Autowired
    public IngredientDao(DSLContext dslContext) {
        super(IngredientTable.INGREDIENT_TABLE, Ingredient.class, dslContext);
    }


    @Override
    public Short getId(Ingredient ingredient) {
        return ofNullable(ingredient)
                .map(Ingredient::getId)
                .orElse(null);
    }


    /**
     *    Get the {@link List} of {@link Ingredient}s which identifiers match with the
     * given ones.
     *
     * @param ids
     *    {@link List} of {@link Ingredient#id} to find
     *
     * @return {@link List} of {@link Ingredient}s
     */
    public List<Ingredient> findByIds(Short... ids) {
        return fetch(IngredientTable.INGREDIENT_TABLE.ID, ids);
    }


    /**
     * Get the {@link Ingredient}s which identifier matches with the given one.
     *
     * @param id
     *    {@link Ingredient#id} to find
     *
     * @return {@link Optional} with the {@link Ingredient} which identifier matches with the given one.
     *         {@link Optional#empty()} otherwise.
     */
    public Optional<Ingredient> findOptionalById(Short id) {
        return fetchOptional(IngredientTable.INGREDIENT_TABLE.ID, id);
    }


    /**
     *    Get the {@link List} of {@link Ingredient}s which names match with the
     * given ones.
     *
     * @param names
     *    {@link List} of {@link Ingredient#name} to find
     *
     * @return {@link List} of {@link Ingredient}s
     */
    public List<Ingredient> findByNames(String... names) {
        return fetch(IngredientTable.INGREDIENT_TABLE.NAME, names);
    }


    /**
     * Get the {@link Ingredient}s which name matches with the given one.
     *
     * @param name
     *    {@link Ingredient#name} to find
     *
     * @return {@link Optional} with the {@link Ingredient} which name matches with the given one.
     *         {@link Optional#empty()} otherwise.
     */
    public Optional<Ingredient> findByName(String name) {
        return fetchOptional(IngredientTable.INGREDIENT_TABLE.NAME, name);
    }

}
