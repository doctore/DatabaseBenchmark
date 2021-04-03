package com.database.benchmark.r2dbc.model.converter;

import com.database.benchmark.r2dbc.model.Orders;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.Date;
import java.util.LinkedHashSet;

@ReadingConverter
public class OrdersConverter implements Converter<Row, Orders> {

    @Override
    public Orders convert(Row row) {
        return new Orders(
                row.get("orders_id", Integer.class),
                row.get("orders_code", String.class),
                row.get("orders_created", Date.class),
                new LinkedHashSet<>()
        );
    }

}
