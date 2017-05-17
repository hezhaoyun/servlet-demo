package cn.ws38.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertQueryDao {

    public void save() {

        final String sql = "INSERT INTO test(name, age) values(?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "zhaoyun");
            preparedStatement.setInt(2, 37);

            preparedStatement.execute();
            connection.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询数据
     */
    public String query() {

        final String sql = "SELECT id, name, age FROM test order by id desc ";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            String result = null;

            while (rs.next()) {
                result = rs.getString("id");
                result += " - " + rs.getString("name");
                result += " - " + rs.getInt("age");
            }

            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) try {
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            if (connection != null) try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}