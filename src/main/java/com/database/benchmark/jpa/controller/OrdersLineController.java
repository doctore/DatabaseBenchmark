package com.database.benchmark.jpa.controller;

import com.database.benchmark.jpa.configuration.RestRoutes;
import com.database.benchmark.jpa.model.OrdersLine;
import com.database.benchmark.jpa.service.OrdersLineService;
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
@RestController(value = "ordersLineControllerJPA")
@RequestMapping(value = RestRoutes.ORDERS_LINE.ROOT)
@Validated
public class OrdersLineController {

    @Lazy
    private final OrdersLineService service;


    @GetMapping("/{lowerId}/{upperId}")
    public ResponseEntity<List<OrdersLine>> findBetweenIdsWithOrdersAndPizza(@PathVariable @Positive Integer lowerId,
                                                                             @PathVariable @Positive Integer upperId) {
        return new ResponseEntity(service.findBetweenIdsWithOrdersAndPizza(lowerId, upperId), OK);
    }

}
