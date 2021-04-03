package com.database.benchmark.jooq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderLineDto {

    private Integer id;

    private Integer ordersId;

    @Valid
    private OrderDto order;

    @NotNull
    @Valid
    private PizzaDto pizza;

    @NotNull
    @Positive
    private Short amount;

    @NotNull
    @Positive
    private Double cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLineDto that = (OrderLineDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
