package com.database.benchmark.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
@Table
public class Ingredient {

    @Id
    @NotNull
    @Positive
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    private String name;

}