package com.database.benchmark.r2dbc.model.converter;

import com.database.benchmark.r2dbc.model.Pizza;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.LinkedHashSet;

@ReadingConverter
public class PizzaConverter implements Converter<Row, Pizza> {

    @Override
    public Pizza convert(Row row) {
        return new Pizza(
                row.get("pizza_id", Integer.class),
                row.get("pizza_name", String.class),
                row.get("pizza_cost", Double.class),
                new LinkedHashSet<>()
        );
    }

}
