package com.girardsimon.testreflection.util;

import com.girardsimon.testreflection.annotations.Column;
import com.girardsimon.testreflection.annotations.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Metamodel {

    private final Class<?> clss;

    public static Metamodel of(Class<?> clss) {
        return new Metamodel(clss);
    }

    public Metamodel(Class<?> clss) {
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

    public String buildInsertRequest() {
        List<String> columnNames = buildColumnNames();

        String columnElement = String.join(", ", columnNames);

        String questionMarksElement = buildQuestionMarksElement();

        return "INSERT INTO " + this.clss.getSimpleName() + "(" + columnElement + ") VALUES (" +  questionMarksElement + ")";
    }

    public String buildSelectRequest() {
        List<String> columnNames = buildColumnNames();

        String columnElement = String.join(", ", columnNames);

        return "select " + columnElement + " from " + this.clss.getSimpleName() +
                " where " + getPrimaryKey().getName() + " = ?";
    }

    private String buildQuestionMarksElement() {
        int numbersOfColumns = getColumns().size() + 1;
        return IntStream.range(0, numbersOfColumns).mapToObj(index -> "?").collect(Collectors.joining(", "));
    }

    private List<String> buildColumnNames() {
        String primaryKeyColumnName = getPrimaryKey().getName();
        List<String> columnNames = getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
        columnNames.add(0, primaryKeyColumnName);
        return columnNames;
    }
}
