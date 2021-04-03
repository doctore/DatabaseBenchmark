package com.database.benchmark.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Data
@NoArgsConstructor
@Table("orders_line")
public class OrdersLine {

    @Id
    @NotNull
    @Positive
    private Integer id;

    @NotNull
    private Orders orders;

    @ManyToOne
    @NotNull
    private Pizza pizza;

    @NotNull
    private Double cost;

    @NotNull
    @Positive
    private Integer amount;

}
