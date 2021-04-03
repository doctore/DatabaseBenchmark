package com.database.benchmark.jooq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
public class Pizza implements IModel, Serializable {

    private static final long serialVersionUID = -1229319586;

    private Short id;

    @NotNull
    @Size(min=1, max=64)
    private String name;

    @NotNull
    @Positive
    private Double cost;

    @Override
    public boolean isNew() {
        return null == id;
    }

}
