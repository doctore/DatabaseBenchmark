package com.database.benchmark.jooq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrdersLine implements IModel, Serializable {

    private static final long serialVersionUID = 1518934662;

    private Integer id;

    @NotNull
    @Positive
    private Integer orderId;

    @NotNull
    @Positive
    private Short pizzaId;

    @NotNull
    @Positive
    private Short amount;

    @NotNull
    @Positive
    private Double cost;


    @Override
    public boolean isNew() {
        return null == id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersLine ordersLine = (OrdersLine) o;
        return null == id ? (orderId.equals(ordersLine.orderId) &&  pizzaId.equals(ordersLine.pizzaId))
                          : id.equals(ordersLine.id);
    }

    @Override
    public int hashCode() {
        return null == id ? Objects.hash(orderId) + Objects.hash(pizzaId) : Objects.hash(id);
    }

}
