package com.database.benchmark.r2dbc.repository.custom;

import com.database.benchmark.r2dbc.model.OrdersLine;
import com.database.benchmark.r2dbc.model.mapper.OrdersLineMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.database.benchmark.r2dbc.repository.OrdersLineRepository.ORDERS_JOIN_ORDERS_LINE;
import static com.database.benchmark.r2dbc.repository.OrdersLineRepository.ORDERS_LINE_ALIAS;
import static com.database.benchmark.r2dbc.repository.OrdersLineRepository.ORDERS_LINE_ORDER_BY_ID;
import static com.database.benchmark.r2dbc.repository.OrdersLineRepository.ORDERS_LINE_SELECT;
import static com.database.benchmark.r2dbc.repository.OrdersLineRepository.ORDERS_LINE_TABLE;
import static com.database.benchmark.r2dbc.repository.OrdersLineRepository.PIZZA_JOIN_ORDERS_LINE;
import static com.database.benchmark.r2dbc.repository.OrdersRepository.ORDERS_SELECT;
import static com.database.benchmark.r2dbc.repository.PizzaRepository.PIZZA_SELECT;

@AllArgsConstructor
@Repository(value = "customOrdersLineRepositoryR2DBC")
public class CustomOrdersLineRepositoryImpl implements CustomOrdersLineRepository {


    @Lazy
    private final R2dbcEntityTemplate template;

    /*
    private DatabaseClient client;

    @Autowired
    public CustomOrdersLineRepositoryImpl(DatabaseClient client) {
        this.client = client;
    }
     */


    @Override
    public Flux<OrdersLine> findAllWithOrdersAndPizza() {
        return this.template.getDatabaseClient()
                .sql(buildQueryWithOrdersAndPizza() + ORDERS_LINE_ORDER_BY_ID)
                .map(OrdersLineMapper.mapper())
                .all();

        /*
        return client.execute(buildQueryWithOrdersAndPizza()
                + ORDERS_LINE_ORDER_BY_ID)
                .map(OrdersLineMapper.mapper())
                .all();
         */
    }

    public Flux<OrdersLine> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId) {
        return this.template.getDatabaseClient()
                .sql(buildQueryWithOrdersAndPizza()
                                + "where " + ORDERS_LINE_TABLE + ".id between :lowerId and :upperId "
                                + ORDERS_LINE_ORDER_BY_ID)
                .bind("lowerId", lowerId)
                .bind("upperId", upperId)
                .map(OrdersLineMapper.mapper())
                .all();

        /*
        return client.execute(buildQueryWithOrdersAndPizza()
                + "where " + ORDERS_LINE_TABLE + ".id between :lowerId and :upperId "
                + ORDERS_LINE_ORDER_BY_ID)
                .bind("lowerId", lowerId)
                .bind("upperId", upperId)
                .map(OrdersLineMapper.mapper())
                .all();
         */
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
