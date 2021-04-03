/*
 * This file is generated by jOOQ.
 */
package com.database.benchmark.jooq.model.internal.tables.records;

import com.database.benchmark.jooq.model.internal.tables.IngredientTable;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IngredientRecord extends UpdatableRecordImpl<IngredientRecord> implements Record2<Short, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.ingredient.id</code>.
     */
    public void setId(Short value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.ingredient.id</code>.
     */
    public Short getId() {
        return (Short) get(0);
    }

    /**
     * Setter for <code>public.ingredient.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.ingredient.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Short> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Short, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Short, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Short> field1() {
        return IngredientTable.INGREDIENT_TABLE.ID;
    }

    @Override
    public Field<String> field2() {
        return IngredientTable.INGREDIENT_TABLE.NAME;
    }

    @Override
    public Short component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Short value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public IngredientRecord value1(Short value) {
        setId(value);
        return this;
    }

    @Override
    public IngredientRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public IngredientRecord values(Short value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached IngredientRecord
     */
    public IngredientRecord() {
        super(IngredientTable.INGREDIENT_TABLE);
    }

    /**
     * Create a detached, initialised IngredientRecord
     */
    public IngredientRecord(Short id, String name) {
        super(IngredientTable.INGREDIENT_TABLE);

        setId(id);
        setName(name);
    }
}