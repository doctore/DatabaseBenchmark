package com.database.benchmark.r2dbc.repository;

import com.database.benchmark.r2dbc.model.Orders;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ordersRepositoryR2DBC")
public interface OrdersRepository extends R2dbcRepository<Orders, Integer> {

    String ORDERS_TABLE = "orders";
    String ORDERS_SELECT = ORDERS_TABLE + ".id as orders_id, "
                         + ORDERS_TABLE + ".code as orders_code, "
                         + ORDERS_TABLE + ".created as orders_created ";
    String ORDERS_ALIAS = ORDERS_TABLE + " " + ORDERS_TABLE + " ";
    String ORDERS_ORDER_BY_ID = "order by " + ORDERS_TABLE + ".id ";

}
