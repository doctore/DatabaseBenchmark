/*
 * This file is generated by jOOQ.
 */
package com.database.benchmark.jooq.model.internal.tables;

import com.database.benchmark.jooq.model.internal.Keys;
import com.database.benchmark.jooq.model.internal.Public;
import com.database.benchmark.jooq.model.internal.tables.records.PizzaIngredientRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class PizzaIngredientTable extends TableImpl<PizzaIngredientRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.pizza_ingredient</code>
     */
    public static final PizzaIngredientTable PIZZA_INGREDIENT_TABLE = new PizzaIngredientTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PizzaIngredientRecord> getRecordType() {
        return PizzaIngredientRecord.class;
    }

    /**
     * The column <code>public.pizza_ingredient.pizza_id</code>.
     */
    public final TableField<PizzaIngredientRecord, Short> PIZZA_ID = createField(DSL.name("pizza_id"), SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>public.pizza_ingredient.ingredient_id</code>.
     */
    public final TableField<PizzaIngredientRecord, Short> INGREDIENT_ID = createField(DSL.name("ingredient_id"), SQLDataType.SMALLINT.nullable(false), this, "");

    private PizzaIngredientTable(Name alias, Table<PizzaIngredientRecord> aliased) {
        this(alias, aliased, null);
    }

    private PizzaIngredientTable(Name alias, Table<PizzaIngredientRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.pizza_ingredient</code> table reference
     */
    public PizzaIngredientTable(String alias) {
        this(DSL.name(alias), PIZZA_INGREDIENT_TABLE);
    }

    /**
     * Create an aliased <code>public.pizza_ingredient</code> table reference
     */
    public PizzaIngredientTable(Name alias) {
        this(alias, PIZZA_INGREDIENT_TABLE);
    }

    /**
     * Create a <code>public.pizza_ingredient</code> table reference
     */
    public PizzaIngredientTable() {
        this(DSL.name("pizza_ingredient"), null);
    }

    public <O extends Record> PizzaIngredientTable(Table<O> child, ForeignKey<O, PizzaIngredientRecord> key) {
        super(child, key, PIZZA_INGREDIENT_TABLE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<PizzaIngredientRecord> getPrimaryKey() {
        return Keys.PIZZA_INGREDIENT_PK;
    }

    @Override
    public List<UniqueKey<PizzaIngredientRecord>> getKeys() {
        return Arrays.<UniqueKey<PizzaIngredientRecord>>asList(Keys.PIZZA_INGREDIENT_PK);
    }

    @Override
    public List<ForeignKey<PizzaIngredientRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PizzaIngredientRecord, ?>>asList(Keys.PIZZA_INGREDIENT__PIZZA_INGREDIENT_PIZZA_ID_FK, Keys.PIZZA_INGREDIENT__PIZZA_INGREDIENT_INGREDIENT_ID_FK);
    }

    private transient PizzaTable _pizza;
    private transient IngredientTable _ingredient;

    public PizzaTable pizza() {
        if (_pizza == null)
            _pizza = new PizzaTable(this, Keys.PIZZA_INGREDIENT__PIZZA_INGREDIENT_PIZZA_ID_FK);

        return _pizza;
    }

    public IngredientTable ingredient() {
        if (_ingredient == null)
            _ingredient = new IngredientTable(this, Keys.PIZZA_INGREDIENT__PIZZA_INGREDIENT_INGREDIENT_ID_FK);

        return _ingredient;
    }

    @Override
    public PizzaIngredientTable as(String alias) {
        return new PizzaIngredientTable(DSL.name(alias), this);
    }

    @Override
    public PizzaIngredientTable as(Name alias) {
        return new PizzaIngredientTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PizzaIngredientTable rename(String name) {
        return new PizzaIngredientTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PizzaIngredientTable rename(Name name) {
        return new PizzaIngredientTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Short, Short> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}