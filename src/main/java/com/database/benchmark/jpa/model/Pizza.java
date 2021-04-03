package com.database.benchmark.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
@Entity
@Table
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_generator")
    @SequenceGenerator(name = "pizza_generator", sequenceName = "pizza_id_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Positive
    private Double cost;

    /*
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "pizza_ingredient",
               inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
    private Set<Ingredient> ingredients = new LinkedHashSet<>();
     */

}
