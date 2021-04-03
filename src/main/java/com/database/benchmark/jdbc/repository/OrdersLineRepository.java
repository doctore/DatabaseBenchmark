package com.database.benchmark.jdbc.repository;

import com.database.benchmark.jdbc.model.OrdersLine;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.database.benchmark.jdbc.model.mapper.OrdersLineMapper.ordersLineRowMapper;
import static com.database.benchmark.jdbc.repository.OrdersRepository.ORDERS_ALIAS;
import static com.database.benchmark.jdbc.repository.OrdersRepository.ORDERS_SELECT;
import static com.database.benchmark.jdbc.repository.OrdersRepository.ORDERS_TABLE;
import static com.database.benchmark.jdbc.repository.PizzaRepository.PIZZA_ALIAS;
import static com.database.benchmark.jdbc.repository.PizzaRepository.PIZZA_SELECT;
import static com.database.benchmark.jdbc.repository.PizzaRepository.PIZZA_TABLE;

@AllArgsConstructor
@Repository(value = "ordersLineRepositoryJDBC")
public class OrdersLineRepository {

    public static final String ORDERS_LINE_TABLE = "orders_line";
    public static final String ORDERS_LINE_SELECT = ORDERS_LINE_TABLE + ".id as orders_line_id, "
                                                  + ORDERS_LINE_TABLE + ".cost as orders_line_cost, "
                                                  + ORDERS_LINE_TABLE + ".amount as orders_line_amount ";
    public static final String ORDERS_LINE_ALIAS = ORDERS_LINE_TABLE + " " + ORDERS_LINE_TABLE + " ";
    public static final String ORDERS_LINE_ORDER_BY_ID = "order by " + ORDERS_LINE_TABLE + ".id ";
    public static final String ORDERS_JOIN_ORDERS_LINE = ORDERS_ALIAS + " ON " + ORDERS_TABLE + ".id = " + ORDERS_LINE_TABLE + ".orders_id ";
    public static final String PIZZA_JOIN_ORDERS_LINE = PIZZA_ALIAS + " ON " + PIZZA_TABLE + ".id = " + ORDERS_LINE_TABLE + ".pizza_id ";

    @Lazy
    private final NamedParameterJdbcTemplate jdbcTemplate;


    public List<OrdersLine> findAllWithOrdersAndPizza() {
        return jdbcTemplate.query(
                buildQueryWithOrdersAndPizza()
                        + ORDERS_LINE_ORDER_BY_ID
                ,ordersLineRowMapper
        );
    }


    public List<OrdersLine> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId) {
        return jdbcTemplate.query(
                buildQueryWithOrdersAndPizza()
                        + "where " + ORDERS_LINE_TABLE + ".id between :lowerId and :upperId "
                        + ORDERS_LINE_ORDER_BY_ID
                ,Map.ofEntries(Map.entry("lowerId", lowerId), Map.entry("upperId", upperId))
                ,ordersLineRowMapper
        );
    }


    private String buildQueryWithOrdersAndPizza() {
        return "select " + ORDERS_LINE_SELECT
              + "      ," + ORDERS_SELECT
              + "      ," + PIZZA_SELECT
              + "from " + ORDERS_LINE_ALIAS
              + "left join " + ORDERS_JOIN_ORDERS_LINE
              + "left join " + PIZZA_JOIN_ORDERS_LINE;
    }

}
