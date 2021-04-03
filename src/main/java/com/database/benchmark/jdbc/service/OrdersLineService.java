package com.database.benchmark.jdbc.service;

import com.database.benchmark.jdbc.model.OrdersLine;
import com.database.benchmark.jdbc.repository.OrdersLineRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service(value = "ordersLineServiceJDBC")
public class OrdersLineService {

    @Lazy
    private final OrdersLineRepository repository;


    public List<OrdersLine> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId) {
        return repository.findBetweenIdsWithOrdersAndPizza(lowerId, upperId);
    }

}
