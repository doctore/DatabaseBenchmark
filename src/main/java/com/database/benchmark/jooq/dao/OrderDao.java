package com.database.benchmark.jooq.dao;

import com.database.benchmark.jooq.dto.OrderDto;
import com.database.benchmark.jooq.dto.OrderLineDto;
import com.database.benchmark.jooq.dto.PizzaDto;
import com.database.benchmark.jooq.model.Orders;
import com.database.benchmark.jooq.model.internal.tables.OrdersTable;
import com.database.benchmark.jooq.model.internal.tables.OrdersLineTable;
import com.database.benchmark.jooq.model.internal.tables.PizzaTable;
import com.database.benchmark.jooq.model.internal.tables.records.OrdersRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record10;
import org.jooq.SelectOnConditionStep;
import org.jooq.Table;
import org.jooq.exception.DataAccessException;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.jooq.impl.DSL.denseRank;
import static org.jooq.impl.DSL.orderBy;

@Repository
public class OrderDao extends ParentDao<OrdersRecord, Orders, Integer> {

    /**
     * Create a new OrderDao with an attached configuration
     */
    @Autowired
    public OrderDao(DSLContext dslContext) {
        super(OrdersTable.ORDERS_TABLE, Orders.class, dslContext);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getId(Orders orders) {
        return ofNullable(orders)
                .map(Orders::getId)
                .orElse(null);
    }


    /**
     *    Get the {@link List} of {@link Orders}s which identifiers match with the
     * given ones.
     *
     * @param ids
     *    {@link List} of {@link Orders#id} to find
     *
     * @return {@link List} of {@link Orders}s
     */
    public List<Orders> findByIds(Integer... ids) {
        return fetch(OrdersTable.ORDERS_TABLE.ID, ids);
    }


    /**
     * Get the {@link Orders}s which identifier matches with the given one.
     *
     * @param id
     *    {@link Orders#id} to find
     *
     * @return {@link Optional} with the {@link Orders} which identifier matches with the given one.
     *         {@link Optional#empty()} otherwise
     */
    public Optional<Orders> findOptionalById(Integer id) {
        return fetchOptional(OrdersTable.ORDERS_TABLE.ID, id);
    }


    /**
     *    Return the {@link OrderDto} and its {@link OrderLineDto} information (and related {@link PizzaDto})
     * of the given {@link OrderDto#id}.
     *
     * @param id
     *    {@link Orders#id} to find
     *
     * @return {@link Optional} with the {@link OrderDto} which identifier matches with the given one.
     *         {@link Optional#empty()} otherwise
     *
     * @throws DataAccessException if there is an error executing the query
     */
    public Optional<OrderDto> fetchToOrderDtoByIdWithOrderLineDto(Integer id) {
        try (ResultSet rs = getOrderWithLinesQuery().where(OrdersTable.ORDERS_TABLE.ID.eq(id))
                                                    .fetchResultSet()) {

            JdbcMapper<OrderDto> jdbcMapper = getJdbcMapper(OrderDto.class, "id", "order_lines_id", "pizza_id");
            return jdbcMapper.stream(rs).findFirst();
        } catch (Exception e) {
            throw new DataAccessException(String.format("There was an error trying to find the order: %d", id), e);
        }
    }


    /**
     * Get the {@link List} of {@link Orders}s which codes match with the given ones.
     *
     * @param codes
     *    {@link List} of {@link Orders#code} to find
     *
     * @return {@link List} of {@link Orders}s
     */
    public List<Orders> findByCodes(String... codes) {
        return fetch(OrdersTable.ORDERS_TABLE.CODE, codes);
    }


    /**
     * Get the {@link Orders}s which code matches with the given one.
     *
     * @param code
     *    {@link Orders#code} to find
     *
     * @return {@link Optional} with the {@link Orders} which code matches with the given one.
     *         {@link Optional#empty()} otherwise.
     */
    public Optional<Orders> findByCode(String code) {
        return fetchOptional(OrdersTable.ORDERS_TABLE.CODE, code);
    }


    /**
     *    Return a "page of {@link OrderDto}" (with its {@link OrderLineDto} and related {@link PizzaDto}), ordered by
     * {@link Orders#created} desc.
     *
     * @param page
     *    Desired page to get (taking into account the value of the given size)
     * @param size
     *    Number of {@link OrderDto}s included in every page
     *
     * @return {@link List} of {@link OrderDto} ordered by {@link Orders#created} desc
     *
     * @throws DataAccessException if there is an error executing the query
     */
    public Set<OrderDto> fetchPageToOrderDtoByIdWithOrderLineDto(int page, int size) {
        if (0 > page || 0 >= size)
            return new LinkedHashSet<>();

        int rankInitial = (page * size) + 1;
        int rankFinal = rankInitial + size - 1;

        // Build the table with required information about orders and their orderlines
        Table<Record10<Integer, String, LocalDateTime, Integer, Integer, Short, Double, Short, String, Double>> orderWithLines =
                getOrderWithLinesQuery().orderBy(OrdersTable.ORDERS_TABLE.CREATED.desc()).asTable("orderWithLines");

        // Use denseRank function to group the required results (and know the final number of rows to return)
        Table<Record> orderWithLinesAndRank = dsl.select(orderWithLines.asterisk()
                                                        ,denseRank().over(orderBy(orderWithLines.field("created").desc())).as("rank"))
                                                 .from(orderWithLines).asTable("orderWithLinesAndRank");

        try (ResultSet rs = dsl.select(orderWithLinesAndRank.field("id"), orderWithLinesAndRank.field("code")
                                      ,   orderWithLinesAndRank.field("created")
                                      ,orderWithLinesAndRank.field("order_lines_id"), orderWithLinesAndRank.field("order_lines_orderId")
                                      ,   orderWithLinesAndRank.field("order_lines_amount"), orderWithLinesAndRank.field("order_lines_cost")
                                      ,orderWithLinesAndRank.field("order_lines_pizza_id"), orderWithLinesAndRank.field("order_lines_pizza_name")
                                      ,   orderWithLinesAndRank.field("order_lines_pizza_cost"))
                               .from(orderWithLinesAndRank)
                               .where(orderWithLinesAndRank.field("rank").cast(Integer.TYPE).between(rankInitial, rankFinal))
                               .fetchResultSet()) {

            JdbcMapper<OrderDto> jdbcMapper = getJdbcMapper(OrderDto.class, "id", "order_lines_id", "pizza_id");
            return jdbcMapper.stream(rs).collect(Collectors.toCollection(LinkedHashSet::new));

        } catch (Exception e) {
            throw new DataAccessException(String.format("There was an error trying to find the orders "
                                                      + "using page: %d and size: %d", page, size), e);
        }
    }


    /**
     * Build the query used to get the information related with {@link Orders}s and its {@link OrderLine}s
     *
     * @return {@link SelectOnConditionStep} with the "partial query"
     */
    private SelectOnConditionStep<Record10<Integer, String, LocalDateTime, Integer, Integer, Short, Double, Short, String, Double>> getOrderWithLinesQuery() {
        OrdersTable ORDER = OrdersTable.ORDERS_TABLE;
        OrdersLineTable ORDER_LINE = OrdersLineTable.ORDERS_LINE_TABLE;
        PizzaTable PIZZA = PizzaTable.PIZZA_TABLE;

        return dsl.select(ORDER.ID, ORDER.CODE, ORDER.CREATED
                         ,ORDER_LINE.ID.as("order_lines_id"), ORDER.ID.as("order_lines_orderId")
                         ,ORDER_LINE.AMOUNT.as("order_lines_amount"), ORDER_LINE.COST.as("order_lines_cost")
                         ,PIZZA.ID.as("order_lines_pizza_id"), PIZZA.NAME.as("order_lines_pizza_name"), PIZZA.COST.as("order_lines_pizza_cost"))
                  .from(ORDER)
                  .leftJoin(ORDER_LINE).on(ORDER_LINE.ORDERS_ID.eq(ORDER.ID))
                  .join(PIZZA).on(PIZZA.ID.eq(ORDER_LINE.PIZZA_ID));
    }

}
