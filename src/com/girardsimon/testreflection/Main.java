package com.girardsimon.testreflection;

import com.girardsimon.testreflection.model.Person;
import com.girardsimon.testreflection.util.ColumnField;
import com.girardsimon.testreflection.util.Metamodel;
import com.girardsimon.testreflection.util.PrimaryKeyField;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Metamodel metamodel = Metamodel.of(Person.class);

        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();

        List<ColumnField> columnFields = metamodel.getColumns();

        System.out.println("Primary key name =  " + primaryKeyField.getName() + ", type = " + primaryKeyField.getType().getSimpleName());

        for (ColumnField c: columnFields) {
            System.out.println("Column name =  " + c.getName() + ", type = " + c.getType().getSimpleName());
        }
    }
}
