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
import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode(of = {"code"})
@Data
@NoArgsConstructor
@Entity
@Table
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_generator")
    @SequenceGenerator(name = "orders_generator", sequenceName = "orders_id_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    private String code;

    @NotNull
    private Date created;

    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orders_id")
    private Set<OrdersLine> ordersLines = new LinkedHashSet<>();
     */

}
