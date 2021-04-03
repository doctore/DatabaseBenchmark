package com.database.benchmark.r2dbc.model.mapper;

import com.database.benchmark.r2dbc.model.OrdersLine;
import com.database.benchmark.r2dbc.model.converter.OrdersConverter;
import com.database.benchmark.r2dbc.model.converter.OrdersLineConverter;
import com.database.benchmark.r2dbc.model.converter.PizzaConverter;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.experimental.UtilityClass;

import java.util.function.BiFunction;

@UtilityClass
public class OrdersLineMapper {

    public static final OrdersLineConverter ORDERS_LINE_CONVERTER = new OrdersLineConverter();
    public static final OrdersConverter ORDERS_CONVERTER = new OrdersConverter();
    public static final PizzaConverter PIZZA_CONVERTER = new PizzaConverter();


    public static BiFunction<Row, RowMetadata, OrdersLine> mapper() {
        return (row, rowMetadata) -> {
            OrdersLine ordersLine = ORDERS_LINE_CONVERTER.convert(row);
            ordersLine.setOrders(ORDERS_CONVERTER.convert(row));
            ordersLine.setPizza(PIZZA_CONVERTER.convert(row));
            return ordersLine;
        };
    }

}
