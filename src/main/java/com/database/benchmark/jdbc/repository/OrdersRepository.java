package com.database.benchmark.jdbc.repository;

import com.database.benchmark.jdbc.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.database.benchmark.jdbc.model.mapper.OrdersMapper.ordersWithOrdersLinesResultExtractor;
import static com.database.benchmark.jdbc.repository.OrdersLineRepository.ORDERS_LINE_ALIAS;
import static com.database.benchmark.jdbc.repository.OrdersLineRepository.ORDERS_LINE_SELECT;
import static com.database.benchmark.jdbc.repository.OrdersLineRepository.ORDERS_LINE_TABLE;
import static com.database.benchmark.jdbc.repository.OrdersLineRepository.PIZZA_JOIN_ORDERS_LINE;
import static com.database.benchmark.jdbc.repository.PizzaRepository.PIZZA_SELECT;

@AllArgsConstructor
@Repository(value = "ordersRepositoryJDBC")
public class OrdersRepository {

    public static final String ORDERS_TABLE = "orders";
    public static final String ORDERS_SELECT = ORDERS_TABLE + ".id as orders_id, "
                                             + ORDERS_TABLE + ".code as orders_code, "
                                             + ORDERS_TABLE + ".created as orders_created ";
    public static final String ORDERS_ALIAS = ORDERS_TABLE + " " + ORDERS_TABLE + " ";
    public static final String ORDERS_ORDER_BY_ID = "order by " + ORDERS_TABLE + ".id ";
    public static final String ORDERS_LINE_JOIN_ORDER = ORDERS_LINE_ALIAS + " ON " + ORDERS_LINE_TABLE + ".orders_id = " + ORDERS_TABLE + ".id ";


    @Lazy
    private final NamedParameterJdbcTemplate jdbcTemplate;


    public List<Orders> findAllWithOrdersLines() {
        return jdbcTemplate.query(
                buildQueryWithOrdersLine()
                  + ORDERS_ORDER_BY_ID,
                ordersWithOrdersLinesResultExtractor
        );
    }


    public List<Orders> findLessGivenIdWithOrdersLines(Integer id) {
        return jdbcTemplate.query(
                buildQueryWithOrdersLine()
                  + "where " + ORDERS_TABLE + ".id < :id "
                  + ORDERS_ORDER_BY_ID
                ,Map.of("id", id)
                ,ordersWithOrdersLinesResultExtractor
        );
    }

    private String buildQueryWithOrdersLine() {
        return "select " + ORDERS_SELECT
             + "      ," + ORDERS_LINE_SELECT
             + "      ," + PIZZA_SELECT + " "
             + "from " + ORDERS_ALIAS
             + "left join " + ORDERS_LINE_JOIN_ORDER
             + "left join " + PIZZA_JOIN_ORDERS_LINE;
    }

}
