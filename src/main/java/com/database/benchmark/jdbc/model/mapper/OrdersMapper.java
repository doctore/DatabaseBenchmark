package com.database.benchmark.jdbc.model.mapper;

import com.database.benchmark.jdbc.model.Orders;
import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static com.database.benchmark.jdbc.model.mapper.OrdersLineMapper.ordersLineRowMapper;

@UtilityClass
public class OrdersMapper {

    public static final RowMapper<Orders> ordersRowMapper = (resultSet, rowNum) ->
            new Orders(
                    resultSet.getInt("orders_id"),
                    resultSet.getString("orders_code"),
                    resultSet.getDate("orders_created"),
                    new LinkedHashSet<>()
            );

    public static final ResultSetExtractor<List<Orders>> ordersWithOrdersLinesResultExtractor = (resultSet) -> {
        List<Orders> result = new ArrayList<>();
        Orders orders = null;
        int previousOrdersId = 0;
        while (resultSet.next()) {
            int currentOrdersId = resultSet.getInt("orders_id");
            if (previousOrdersId != currentOrdersId) {
                orders = ordersRowMapper.mapRow(resultSet, 1);
                result.add(orders);
                previousOrdersId = currentOrdersId;
            }
            orders.addOrdersLine(ordersLineRowMapper.mapRow(resultSet, 1));
        }
        return result;
    };

}
