package com.database.benchmark.jooq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
public class PizzaDto {

    private Short id;

    @NotNull
    @Size(min=1, max=64)
    private String name;

    @NotNull
    @Positive
    private Double cost;

    @Valid
    List<IngredientDto> ingredients;

}
