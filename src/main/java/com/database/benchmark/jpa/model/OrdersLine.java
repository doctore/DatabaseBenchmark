package com.database.benchmark.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Data
@NoArgsConstructor
@Entity
@Table
public class OrdersLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_line_generator")
    @SequenceGenerator(name = "orders_line_generator", sequenceName = "orders_line_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pizza_id", foreignKey = @ForeignKey(name = "orders_line_pizza_id_fk"))
    @NotNull
    private Pizza pizza;

    @NotNull
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "orders_id", foreignKey = @ForeignKey(name = "orders_line_orders_id_fk"))
    @NotNull
    private Orders orders;

    @NotNull
    @Positive
    private Integer amount;

}
