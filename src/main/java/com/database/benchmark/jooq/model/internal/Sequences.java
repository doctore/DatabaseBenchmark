/*
 * This file is generated by jOOQ.
 */
package com.database.benchmark.jooq.model.internal;

import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.ingredient_id_seq</code>
     */
    public static final Sequence<Short> INGREDIENT_ID_SEQ = Internal.createSequence("ingredient_id_seq", Public.PUBLIC, SQLDataType.SMALLINT.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.orders_id_seq</code>
     */
    public static final Sequence<Integer> ORDERS_ID_SEQ = Internal.createSequence("orders_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.orders_line_id_seq</code>
     */
    public static final Sequence<Integer> ORDERS_LINE_ID_SEQ = Internal.createSequence("orders_line_id_seq", Public.PUBLIC, SQLDataType.INTEGER.nullable(false), null, null, null, null, false, null);

    /**
     * The sequence <code>public.pizza_id_seq</code>
     */
    public static final Sequence<Short> PIZZA_ID_SEQ = Internal.createSequence("pizza_id_seq", Public.PUBLIC, SQLDataType.SMALLINT.nullable(false), null, null, null, null, false, null);
}