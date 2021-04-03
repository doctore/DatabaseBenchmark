package com.database.benchmark.jpa.service;

import com.database.benchmark.jpa.model.OrdersLine;
import com.database.benchmark.jpa.repository.OrdersLineRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service(value = "ordersLineServiceJPA")
public class OrdersLineService {

    @Lazy
    private final OrdersLineRepository repository;


    public List<OrdersLine> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId) {
        return repository.findByIdBetween(lowerId, upperId);
    }

}
