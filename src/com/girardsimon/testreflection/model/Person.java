package com.girardsimon.testreflection.model;

import com.girardsimon.testreflection.annotations.Column;
import com.girardsimon.testreflection.annotations.PrimaryKey;

public class Person {

    @PrimaryKey
    private long id;
    @Column
    private int age;
    @Column
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static Person of(int age, String name) {
        return new Person(age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
