package com.girardsimon.testreflection;

import com.girardsimon.testreflection.model.Person;
import com.girardsimon.testreflection.orm.EntityManager;

import java.sql.SQLException;

public class WritingObjects {

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        EntityManager<Person> entityManager = EntityManager.of(Person.class);

        Person linda = new Person(31, "Linda");
        Person james = new Person(24, "James");
        Person susan = new Person(34, "Susan");
        Person john = new Person(33, "John");

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);

        entityManager.persist(linda);
        entityManager.persist(james);
        entityManager.persist(susan);
        entityManager.persist(john);

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);
    }
}
