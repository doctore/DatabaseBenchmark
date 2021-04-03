package com.database.benchmark.r2dbc.controller;

import com.database.benchmark.r2dbc.configuration.RestRoutes;
import com.database.benchmark.r2dbc.model.OrdersLine;
import com.database.benchmark.r2dbc.service.OrdersLineService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Positive;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController(value = "ordersLineControllerR2DBC")
@RequestMapping(value = RestRoutes.ORDERS_LINE.ROOT)
@Validated
public class OrdersLineController {

    @Lazy
    private final OrdersLineService service;


    @GetMapping("/{lowerId}/{upperId}")
    public Flux<OrdersLine> findBetweenIdsWithOrdersAndPizzaFlux(@PathVariable @Positive Integer lowerId,
                                                                  @PathVariable @Positive Integer upperId) {
        return service.findBetweenIdsWithOrdersAndPizza(lowerId, upperId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "No orders lines with provided limits")));
    }

}
