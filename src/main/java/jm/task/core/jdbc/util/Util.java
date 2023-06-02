package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    public static final String userName = "postgres";
    public static final String password = "milana27112008";
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";


    public static Connection getPostgreSQLConnection () throws
            SQLException {
        Connection conn = DriverManager.getConnection(URL, userName, password);
        conn.setAutoCommit(false);
        return conn;
    }
    public static Properties getProperties(){
        Properties properties = new Properties();
        properties.setProperty(Environment.URL,URL);
        properties.setProperty(Environment.DRIVER,"org.postgresql.Driver");
        properties.setProperty(Environment.USER,userName);
        properties.setProperty(Environment.PASS,password);
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
        properties.setProperty(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL,"true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        return properties;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperties(getProperties());
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();

    }
}