package cn.ws38.demo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Get the connection from writing DB
 */
public class ConnectionPool {

    private static BasicDataSource dataSource = null;

    public static void init() {

        if (dataSource != null) return;

        try {
            Properties p = new Properties();

            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:MySQL://localhost:3306/demo");
            p.setProperty("username", "root");
            p.setProperty("password", "801129");
            p.setProperty("maxActive", "10");
            p.setProperty("maxIdle", "5");
            p.setProperty("maxWait", "3600000");
            p.setProperty("removeAbandoned", "false");
            p.setProperty("removeAbandonedTimeout", "20");
            p.setProperty("testOnBorrow", "true");
            p.setProperty("logAbandoned", "true");

            dataSource = BasicDataSourceFactory.createDataSource(p);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static synchronized Connection getConnection() throws SQLException {

        if (dataSource == null) {
            init();
        }

        Connection connection = null;

        if (dataSource != null) {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        }

        return connection;
    }
}