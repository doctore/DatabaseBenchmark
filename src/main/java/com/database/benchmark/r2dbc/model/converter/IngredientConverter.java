package com.database.benchmark.r2dbc.model.converter;

import com.database.benchmark.r2dbc.model.Ingredient;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class IngredientConverter implements Converter<Row, Ingredient> {

    @Override
    public Ingredient convert(Row row) {
        return new Ingredient(
                row.get("ingredient_id", Integer.class),
                row.get("ingredient_name", String.class)
        );
    }

}
