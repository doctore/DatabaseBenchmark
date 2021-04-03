package com.database.benchmark.r2dbc.model.converter;

import com.database.benchmark.r2dbc.model.OrdersLine;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class OrdersLineConverter implements Converter<Row, OrdersLine> {

    @Override
    public OrdersLine convert(Row row) {
        return new OrdersLine(
                row.get("orders_line_id", Integer.class),
                null,
                null,
                row.get("orders_line_cost", Double.class),
                row.get("orders_line_amount", Integer.class)
        );
    }

}
