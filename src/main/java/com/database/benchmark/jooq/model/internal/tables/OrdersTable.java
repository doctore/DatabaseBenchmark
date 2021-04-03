/*
 * This file is generated by jOOQ.
 */
package com.database.benchmark.jooq.model.internal.tables;

import com.database.benchmark.jooq.model.internal.Indexes;
import com.database.benchmark.jooq.model.internal.Keys;
import com.database.benchmark.jooq.model.internal.Public;
import com.database.benchmark.jooq.model.internal.tables.records.OrdersRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrdersTable extends TableImpl<OrdersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.orders</code>
     */
    public static final OrdersTable ORDERS_TABLE = new OrdersTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrdersRecord> getRecordType() {
        return OrdersRecord.class;
    }

    /**
     * The column <code>public.orders.id</code>.
     */
    public final TableField<OrdersRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.orders.code</code>.
     */
    public final TableField<OrdersRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>public.orders.created</code>.
     */
    public final TableField<OrdersRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private OrdersTable(Name alias, Table<OrdersRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrdersTable(Name alias, Table<OrdersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.orders</code> table reference
     */
    public OrdersTable(String alias) {
        this(DSL.name(alias), ORDERS_TABLE);
    }

    /**
     * Create an aliased <code>public.orders</code> table reference
     */
    public OrdersTable(Name alias) {
        this(alias, ORDERS_TABLE);
    }

    /**
     * Create a <code>public.orders</code> table reference
     */
    public OrdersTable() {
        this(DSL.name("orders"), null);
    }

    public <O extends Record> OrdersTable(Table<O> child, ForeignKey<O, OrdersRecord> key) {
        super(child, key, ORDERS_TABLE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ORDER_CODE_UINDEX);
    }

    @Override
    public Identity<OrdersRecord, Integer> getIdentity() {
        return (Identity<OrdersRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<OrdersRecord> getPrimaryKey() {
        return Keys.ORDERS_PK;
    }

    @Override
    public List<UniqueKey<OrdersRecord>> getKeys() {
        return Arrays.<UniqueKey<OrdersRecord>>asList(Keys.ORDERS_PK);
    }

    @Override
    public OrdersTable as(String alias) {
        return new OrdersTable(DSL.name(alias), this);
    }

    @Override
    public OrdersTable as(Name alias) {
        return new OrdersTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdersTable rename(String name) {
        return new OrdersTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdersTable rename(Name name) {
        return new OrdersTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}