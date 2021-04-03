package com.database.benchmark;

import com.database.benchmark.jooq.dao.OrderLineDao;
import com.database.benchmark.jooq.dto.OrderLineDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class DatabaseBenchmarkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseBenchmarkApplication.class, args);

        /*
        ConfigurableApplicationContext context = SpringApplication.run(DatabaseBenchmarkApplication.class, args);
        OrderLineDao orderLineDao = context.getBean(OrderLineDao.class);

        List<OrderLineDto> result = orderLineDao.fetchToOrderLineDtoByOrderIdWithOrderAndPizzaDto(10, 20);
        int a = 0;
         */
    }

}
