package com.database.benchmark.jpa.repository;

import com.database.benchmark.jpa.model.OrdersLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "ordersLineRepositoryJPA")
public interface OrdersLineRepository extends JpaRepository<OrdersLine, Integer> {

    List<OrdersLine> findByIdBetween(int lowerId, int upperId);

}
