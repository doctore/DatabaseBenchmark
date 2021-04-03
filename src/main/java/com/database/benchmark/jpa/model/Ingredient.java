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
import javax.validation.constraints.Size;

@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
@Entity
@Table
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ingredient_generator")
    @SequenceGenerator(name = "ingredient_generator", sequenceName = "ingredient_id_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    private String name;

}