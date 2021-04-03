package com.database.benchmark.r2dbc.repository;

import com.database.benchmark.r2dbc.model.OrdersLine;
import com.database.benchmark.r2dbc.repository.custom.CustomOrdersLineRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import static com.database.benchmark.jdbc.repository.OrdersRepository.ORDERS_ALIAS;
import static com.database.benchmark.r2dbc.repository.OrdersRepository.ORDERS_TABLE;
import static com.database.benchmark.r2dbc.repository.PizzaRepository.PIZZA_ALIAS;
import static com.database.benchmark.r2dbc.repository.PizzaRepository.PIZZA_TABLE;

@Repository(value = "ordersLineRepositoryR2DBC")
public interface OrdersLineRepository extends R2dbcRepository<OrdersLine, Integer>, CustomOrdersLineRepository {

    String ORDERS_LINE_TABLE = "orders_line";
    String ORDERS_LINE_SELECT = ORDERS_LINE_TABLE + ".id as orders_line_id, "
                              + ORDERS_LINE_TABLE + ".cost as orders_line_cost, "
                              + ORDERS_LINE_TABLE + ".amount as orders_line_amount ";
    String ORDERS_LINE_ALIAS = ORDERS_LINE_TABLE + " " + ORDERS_LINE_TABLE + " ";
    String ORDERS_LINE_ORDER_BY_ID = "order by " + ORDERS_LINE_TABLE + ".id ";
    String ORDERS_JOIN_ORDERS_LINE = ORDERS_ALIAS + " ON " + ORDERS_TABLE + ".id = " + ORDERS_LINE_TABLE + ".orders_id ";
    String PIZZA_JOIN_ORDERS_LINE = PIZZA_ALIAS + " ON " + PIZZA_TABLE + ".id = " + ORDERS_LINE_TABLE + ".pizza_id ";

}
