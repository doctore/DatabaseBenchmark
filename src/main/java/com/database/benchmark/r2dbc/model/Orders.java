package com.database.benchmark.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode(of = {"code"})
@Data
@NoArgsConstructor
@Table
public class Orders {

    @Id
    @NotNull
    @Positive
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    private String code;

    @NotNull
    private Date created;

    private Set<OrdersLine> ordersLines = new LinkedHashSet<>();

    public void addOrdersLine(OrdersLine ordersLine) {
        this.ordersLines.add(ordersLine);
    }
}
