package com.database.benchmark.jdbc.model.mapper;

import com.database.benchmark.jdbc.model.OrdersLine;
import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.RowMapper;

import static com.database.benchmark.jdbc.model.mapper.OrdersMapper.ordersRowMapper;
import static com.database.benchmark.jdbc.model.mapper.PizzaMapper.pizzaRowMapper;

@UtilityClass
public class OrdersLineMapper {

    public static final RowMapper<OrdersLine> ordersLineRowMapper = (resultSet, rowNum) ->
            new OrdersLine(
                    resultSet.getInt("orders_line_id"),
                    ordersRowMapper.mapRow(resultSet, 1),
                    pizzaRowMapper.mapRow(resultSet, 1),
                    resultSet.getDouble("orders_line_cost"),
                    resultSet.getInt("orders_line_amount")
            );

}
