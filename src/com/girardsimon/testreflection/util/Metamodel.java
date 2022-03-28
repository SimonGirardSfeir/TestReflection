package com.girardsimon.testreflection.util;

import com.girardsimon.testreflection.annotations.Column;
import com.girardsimon.testreflection.annotations.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Metamodel<T> {

    private final Class<T> clss;

    public static <T> Metamodel<T> of(Class<T> clss) {
        return new Metamodel<>(clss);
    }

    public Metamodel(Class<T> clss) {
        this.clss = clss;
    }

    public PrimaryKeyField getPrimaryKey() {
        Field[] declaredFields = clss.getDeclaredFields();

        for(Field f : declaredFields) {
            PrimaryKey primaryKey = f.getAnnotation(PrimaryKey.class);

            if(primaryKey != null) {
                return new PrimaryKeyField(f);
            }
        }
        throw new IllegalArgumentException("No primary key found in this class " + clss.getSimpleName());
    }

    public List<ColumnField> getColumns() {
        Field[] declaredFields = clss.getDeclaredFields();
        List<ColumnField> columnFields = new ArrayList<>();

        for(Field f : declaredFields) {
            Column column = f.getAnnotation(Column.class);

            if(column != null) {
                ColumnField columnField = new ColumnField(f);
                columnFields.add(columnField);
            }
        }
        return columnFields;
    }
}
