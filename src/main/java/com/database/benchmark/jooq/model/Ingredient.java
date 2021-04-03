package com.database.benchmark.jooq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Data
@NoArgsConstructor
public class Ingredient implements IModel, Serializable {

    private static final long serialVersionUID = 4421336834596630114L;

    private Short id;

    @NotNull
    @Size(min=1, max=64)
    private String name;

    @Override
    public boolean isNew() {
        return null == id;
    }

}
