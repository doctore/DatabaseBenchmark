package com.database.benchmark.jooq.service;

import com.database.benchmark.jooq.dto.OrderLineDto;
import com.database.benchmark.jooq.dao.OrderLineDao;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service(value = "ordersLineServiceJOOQ")
public class OrdersLineService {

    @Lazy
    private final OrderLineDao dao;


    public List<OrderLineDto> findBetweenIdsWithOrdersAndPizza(int lowerId, int upperId) {
        return dao.fetchToOrderLineDtoByOrderIdWithOrderAndPizzaDto(lowerId, upperId);
    }

}
