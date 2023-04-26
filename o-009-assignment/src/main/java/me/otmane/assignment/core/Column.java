package me.otmane.assignment.core;

import me.otmane.assignment.models.Branch;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Column {
    String name();

    boolean insertable() default true;
    boolean updatable() default true;

    boolean primaryKey() default false;

    Class<?> type() default void.class;
    Class<?> references() default void.class;
}

