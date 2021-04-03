package com.database.benchmark.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
@Table
public class Pizza {

    @Id
    @NotNull
    @Positive
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Positive
    private Double cost;

    private Set<Ingredient> ingredients = new LinkedHashSet<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

}
