package com.database.benchmark.jooq.model;

public interface IModel {

    /**
     * Return {@code true} if the current model has not been persisted, {@code false} otherwise.
     */
    boolean isNew();

}
