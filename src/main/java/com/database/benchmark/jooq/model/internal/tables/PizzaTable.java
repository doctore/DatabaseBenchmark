/*
 * This file is generated by jOOQ.
 */
package com.database.benchmark.jooq.model.internal.tables;

import com.database.benchmark.jooq.model.internal.Indexes;
import com.database.benchmark.jooq.model.internal.Keys;
import com.database.benchmark.jooq.model.internal.Public;
import com.database.benchmark.jooq.model.internal.tables.records.PizzaRecord;

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
public class PizzaTable extends TableImpl<PizzaRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.pizza</code>
     */
    public static final PizzaTable PIZZA_TABLE = new PizzaTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PizzaRecord> getRecordType() {
        return PizzaRecord.class;
    }

    /**
     * The column <code>public.pizza.id</code>.
     */
    public final TableField<PizzaRecord, Short> ID = createField(DSL.name("id"), SQLDataType.SMALLINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.pizza.name</code>.
     */
    public final TableField<PizzaRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(64), this, "");

    /**
     * The column <code>public.pizza.cost</code>.
     */
    public final TableField<PizzaRecord, Double> COST = createField(DSL.name("cost"), SQLDataType.DOUBLE.nullable(false), this, "");

    private PizzaTable(Name alias, Table<PizzaRecord> aliased) {
        this(alias, aliased, null);
    }

    private PizzaTable(Name alias, Table<PizzaRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.pizza</code> table reference
     */
    public PizzaTable(String alias) {
        this(DSL.name(alias), PIZZA_TABLE);
    }

    /**
     * Create an aliased <code>public.pizza</code> table reference
     */
    public PizzaTable(Name alias) {
        this(alias, PIZZA_TABLE);
    }

    /**
     * Create a <code>public.pizza</code> table reference
     */
    public PizzaTable() {
        this(DSL.name("pizza"), null);
    }

    public <O extends Record> PizzaTable(Table<O> child, ForeignKey<O, PizzaRecord> key) {
        super(child, key, PIZZA_TABLE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PIZZA_NAME_UINDEX);
    }

    @Override
    public Identity<PizzaRecord, Short> getIdentity() {
        return (Identity<PizzaRecord, Short>) super.getIdentity();
    }

    @Override
    public UniqueKey<PizzaRecord> getPrimaryKey() {
        return Keys.PIZZA_PK;
    }

    @Override
    public List<UniqueKey<PizzaRecord>> getKeys() {
        return Arrays.<UniqueKey<PizzaRecord>>asList(Keys.PIZZA_PK);
    }

    @Override
    public PizzaTable as(String alias) {
        return new PizzaTable(DSL.name(alias), this);
    }

    @Override
    public PizzaTable as(Name alias) {
        return new PizzaTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PizzaTable rename(String name) {
        return new PizzaTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PizzaTable rename(Name name) {
        return new PizzaTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Short, String, Double> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
