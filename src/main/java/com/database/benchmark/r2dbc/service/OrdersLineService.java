package com.database.benchmark.r2dbc.service;

import com.database.benchmark.r2dbc.model.OrdersLine;
import com.database.benchmark.r2dbc.repository.OrdersLineRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service(value = "ordersLineServiceR2DBC")
public class OrdersLineService {

    @Lazy
    private final OrdersLineRepository repository;


    public Flux<OrdersLine> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId) {
        return repository.findBetweenIdsWithOrdersAndPizza(lowerId, upperId);
    }

}
