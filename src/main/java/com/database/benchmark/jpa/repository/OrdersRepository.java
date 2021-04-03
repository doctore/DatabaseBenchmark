package com.database.benchmark.jpa.repository;

import com.database.benchmark.jpa.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ordersRepositoryJPA")
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    /*
    @Query("SELECT DISTINCT o FROM Orders o LEFT JOIN FETCH o.ordersLines ORDER BY o.id")
    List<Orders> findAllWithOrdersLines();

    @Query("SELECT DISTINCT o FROM Orders o LEFT JOIN FETCH o.ordersLines WHERE o.id < :id ORDER BY o.id")
    List<Orders> findLessGivenIdWithOrdersLines(Integer id);
     */

}
