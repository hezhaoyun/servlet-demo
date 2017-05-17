package cn.ws38.demo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 连接数据库工具
 *
 * @author Administrator
 */
public class DBUtils {

    //连接池
    private static BasicDataSource dataSource;

    static {

        try {
            Properties p = new Properties();

            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:MySQL://localhost:3306/mysql");
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到Conneection连接
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 归还Connection
     *
     * @param connection 需要关闭的Connection
     */
    public static void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}