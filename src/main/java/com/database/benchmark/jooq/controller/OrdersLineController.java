package com.database.benchmark.jooq.controller;

import com.database.benchmark.jooq.configuration.RestRoutes;
import com.database.benchmark.jooq.dto.OrderLineDto;
import com.database.benchmark.jooq.service.OrdersLineService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController(value = "ordersLineControllerJOOQ")
@RequestMapping(value = RestRoutes.ORDERS_LINE.ROOT)
@Validated
public class OrdersLineController {

    @Lazy
    private final OrdersLineService service;


    @GetMapping("/{lowerId}/{upperId}")
    public ResponseEntity<List<OrderLineDto>> findBetweenIdsWithOrdersAndPizza(@PathVariable @Positive Integer lowerId,
                                                                               @PathVariable @Positive Integer upperId) {
        return new ResponseEntity(service.findBetweenIdsWithOrdersAndPizza(lowerId, upperId), OK);
    }

}
