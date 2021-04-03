package com.database.benchmark.jooq.dao;

import com.database.benchmark.jooq.dto.OrderLineDto;
import com.database.benchmark.jooq.model.OrdersLine;
import com.database.benchmark.jooq.model.internal.tables.OrdersTable;
import com.database.benchmark.jooq.model.internal.tables.OrdersLineTable;
import com.database.benchmark.jooq.model.internal.tables.PizzaTable;
import com.database.benchmark.jooq.model.internal.tables.records.OrdersLineRecord;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Repository
public class OrderLineDao extends ParentDao<OrdersLineRecord, OrdersLine, Integer> {

    /**
     * Create a new OrderLineDao with an attached configuration
     */
    @Autowired
    public OrderLineDao(DSLContext dslContext) {
        super(OrdersLineTable.ORDERS_LINE_TABLE, OrdersLine.class, dslContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getId(OrdersLine ordersLine) {
        return ofNullable(ordersLine)
                .map(OrdersLine::getId)
                .orElse(null);
    }


    /**
     *    Get the {@link List} of {@link OrdersLine}s which identifiers match with the
     * given ones.
     *
     * @param ids
     *    {@link List} of {@link OrdersLine#id} to find
     *
     * @return {@link List} of {@link OrdersLine}s
     */
    public List<OrdersLine> findByIds(Integer... ids) {
        return fetch(OrdersLineTable.ORDERS_LINE_TABLE.ID, ids);
    }


    /**
     * Get the {@link OrdersLine}s which identifier matches with the given one.
     *
     * @param id
     *    {@link OrdersLine#id} to find
     *
     * @return {@link Optional} with the {@link OrdersLine} which identifier matches with the given one.
     *         {@link Optional#empty()} otherwise.
     */
    public Optional<OrdersLine> findOptionalById(Integer id) {
        return fetchOptional(OrdersLineTable.ORDERS_LINE_TABLE.ID, id);
    }


    /**
     * Get the {@link List} of {@link OrdersLine}s belong to the given {@link Order#id}.
     *
     * @param orderIds
     *    Array of {@link Orders#id}s to find
     *
     * @return {@link List} of {@link OrdersLine}s
     */
    public List<OrdersLine> findByOrderIds(Integer... orderIds) {
        return fetch(OrdersLineTable.ORDERS_LINE_TABLE.ORDERS_ID, orderIds);
    }


    /**
     * Get the {@link List} of {@link OrdersLine}s belong to the given {@link Pizza#id}.
     *
     * @param pizzaIds
     *    Array of {@link Pizza#id}s to find
     *
     * @return {@link List} of {@link OrdersLine}s
     */
    public List<OrdersLine> findByPizzaIds(Short... pizzaIds) {
        return fetch(OrdersLineTable.ORDERS_LINE_TABLE.PIZZA_ID, pizzaIds);
    }


    /**
     * Return the {@link List} of {@link OrderLineDto}s and its {@link PizzaDto} information of the given {@link Order#id}
     *
     * @param orderId
     *    {@link Order#id} to find
     *
     * @return {@link List} of {@link OrderLineDto}s.
     *
     * @throws DataAccessException if there is an error executing the query
     */
    public List<OrderLineDto> fetchToOrderLineDtoByOrderIdWithPizzaDto(Integer orderId) {
        OrdersLineTable ORDER_LINE = OrdersLineTable.ORDERS_LINE_TABLE;
        PizzaTable PIZZA = PizzaTable.PIZZA_TABLE;

        try (ResultSet rs =
                     dsl.select(ORDER_LINE.ID, ORDER_LINE.ORDERS_ID, ORDER_LINE.AMOUNT, ORDER_LINE.COST
                               ,PIZZA.ID.as("pizza_id"), PIZZA.NAME.as("pizza_name"), PIZZA.COST.as("pizza_cost"))
                        .from(ORDER_LINE)
                        .join(PIZZA).on(PIZZA.ID.eq(ORDER_LINE.PIZZA_ID))
                        .where(ORDER_LINE.ORDERS_ID.eq(orderId))
                        .fetchResultSet()) {

            JdbcMapper<OrderLineDto> jdbcMapper = getJdbcMapper(OrderLineDto.class, "id", "pizza_id");
            return jdbcMapper.stream(rs).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataAccessException(String.format("There was an error trying to find the order lines related with the order: %d", orderId), e);
        }
    }


    /**
     * Return the {@link List} of {@link OrderLineDto}s and its {@link PizzaDto} information of the given {@link Order#id}
     *
     * @param lowerId
     *    {@link Order#id} to find
     * @param upperId
     *    {@link Order#id} to find
     *
     * @return {@link List} of {@link OrderLineDto}s.
     *
     * @throws DataAccessException if there is an error executing the query
     */
    public List<OrderLineDto> fetchToOrderLineDtoByOrderIdWithOrderAndPizzaDto(int lowerId, int upperId) {
        OrdersLineTable ORDER_LINE = OrdersLineTable.ORDERS_LINE_TABLE;
        OrdersTable ORDER = OrdersTable.ORDERS_TABLE;
        PizzaTable PIZZA = PizzaTable.PIZZA_TABLE;

        try (ResultSet rs =
                     dsl.select(ORDER_LINE.ID, ORDER_LINE.ORDERS_ID, ORDER_LINE.AMOUNT, ORDER_LINE.COST
                               ,PIZZA.ID.as("pizza_id"), PIZZA.NAME.as("pizza_name"), PIZZA.COST.as("pizza_cost")
                               ,ORDER.ID.as("order_id"), ORDER.CODE.as("order_code"), ORDER.CREATED.as("order_created"))
                             .from(ORDER_LINE)
                             .join(ORDER).on(ORDER.ID.eq(ORDER_LINE.ORDERS_ID))
                             .join(PIZZA).on(PIZZA.ID.eq(ORDER_LINE.PIZZA_ID))
                             .where(ORDER_LINE.ORDERS_ID.between(lowerId).and(upperId))
                             .fetchResultSet()) {

            JdbcMapper<OrderLineDto> jdbcMapper = getJdbcMapper(OrderLineDto.class, "id", "pizza_id", "order_id");
            return jdbcMapper.stream(rs).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataAccessException(String.format("There was an error trying to find the order lines related with the order between: %d and %d",
                    lowerId, upperId), e);
        }
    }

}
