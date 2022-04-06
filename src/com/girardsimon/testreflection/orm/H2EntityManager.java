package com.girardsimon.testreflection.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManagerImpl<T> {

    public Connection buildConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/Documents/Java/TestReflection/db-files/db-pl", "sa", "");
    }
}
