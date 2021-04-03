/*
 * This file is generated by jOOQ.
 */
package com.database.benchmark.jooq.model.internal;

import com.database.benchmark.jooq.model.internal.tables.IngredientTable;
import com.database.benchmark.jooq.model.internal.tables.OrdersTable;
import com.database.benchmark.jooq.model.internal.tables.OrdersLineTable;
import com.database.benchmark.jooq.model.internal.tables.PizzaTable;
import com.database.benchmark.jooq.model.internal.tables.PizzaIngredientTable;
import com.database.benchmark.jooq.model.internal.tables.records.IngredientRecord;
import com.database.benchmark.jooq.model.internal.tables.records.OrdersLineRecord;
import com.database.benchmark.jooq.model.internal.tables.records.OrdersRecord;
import com.database.benchmark.jooq.model.internal.tables.records.PizzaIngredientRecord;
import com.database.benchmark.jooq.model.internal.tables.records.PizzaRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

/**
 * A class modelling foreign key relationships and constraints of tables in 
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<IngredientRecord> INGREDIENT_PK = Internal.createUniqueKey(IngredientTable.INGREDIENT_TABLE, DSL.name("ingredient_pk"), new TableField[] { IngredientTable.INGREDIENT_TABLE.ID }, true);
    public static final UniqueKey<OrdersRecord> ORDERS_PK = Internal.createUniqueKey(OrdersTable.ORDERS_TABLE, DSL.name("orders_pk"), new TableField[] { OrdersTable.ORDERS_TABLE.ID }, true);
    public static final UniqueKey<OrdersLineRecord> ORDERS_LINE_PK = Internal.createUniqueKey(OrdersLineTable.ORDERS_LINE_TABLE, DSL.name("orders_line_pk"), new TableField[] { OrdersLineTable.ORDERS_LINE_TABLE.ID }, true);
    public static final UniqueKey<PizzaRecord> PIZZA_PK = Internal.createUniqueKey(PizzaTable.PIZZA_TABLE, DSL.name("pizza_pk"), new TableField[] { PizzaTable.PIZZA_TABLE.ID }, true);
    public static final UniqueKey<PizzaIngredientRecord> PIZZA_INGREDIENT_PK = Internal.createUniqueKey(PizzaIngredientTable.PIZZA_INGREDIENT_TABLE, DSL.name("pizza_ingredient_pk"), new TableField[] { PizzaIngredientTable.PIZZA_INGREDIENT_TABLE.PIZZA_ID, PizzaIngredientTable.PIZZA_INGREDIENT_TABLE.INGREDIENT_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<OrdersLineRecord, OrdersRecord> ORDERS_LINE__ORDERS_LINE_ORDER_ID_FK = Internal.createForeignKey(OrdersLineTable.ORDERS_LINE_TABLE, DSL.name("orders_line_order_id_fk"), new TableField[] { OrdersLineTable.ORDERS_LINE_TABLE.ORDERS_ID }, Keys.ORDERS_PK, new TableField[] { OrdersTable.ORDERS_TABLE.ID }, true);
    public static final ForeignKey<OrdersLineRecord, PizzaRecord> ORDERS_LINE__ORDERS_LINE_PIZZA_ID_FK = Internal.createForeignKey(OrdersLineTable.ORDERS_LINE_TABLE, DSL.name("orders_line_pizza_id_fk"), new TableField[] { OrdersLineTable.ORDERS_LINE_TABLE.PIZZA_ID }, Keys.PIZZA_PK, new TableField[] { PizzaTable.PIZZA_TABLE.ID }, true);
    public static final ForeignKey<PizzaIngredientRecord, IngredientRecord> PIZZA_INGREDIENT__PIZZA_INGREDIENT_INGREDIENT_ID_FK = Internal.createForeignKey(PizzaIngredientTable.PIZZA_INGREDIENT_TABLE, DSL.name("pizza_ingredient_ingredient_id_fk"), new TableField[] { PizzaIngredientTable.PIZZA_INGREDIENT_TABLE.INGREDIENT_ID }, Keys.INGREDIENT_PK, new TableField[] { IngredientTable.INGREDIENT_TABLE.ID }, true);
    public static final ForeignKey<PizzaIngredientRecord, PizzaRecord> PIZZA_INGREDIENT__PIZZA_INGREDIENT_PIZZA_ID_FK = Internal.createForeignKey(PizzaIngredientTable.PIZZA_INGREDIENT_TABLE, DSL.name("pizza_ingredient_pizza_id_fk"), new TableField[] { PizzaIngredientTable.PIZZA_INGREDIENT_TABLE.PIZZA_ID }, Keys.PIZZA_PK, new TableField[] { PizzaTable.PIZZA_TABLE.ID }, true);
}
