package com.database.benchmark.r2dbc.repository.custom;

import com.database.benchmark.r2dbc.model.OrdersLine;
import reactor.core.publisher.Flux;

public interface CustomOrdersLineRepository {

    Flux<OrdersLine> findAllWithOrdersAndPizza();

    Flux<OrdersLine> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId);

}
