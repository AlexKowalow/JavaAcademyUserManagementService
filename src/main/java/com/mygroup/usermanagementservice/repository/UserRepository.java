package com.mygroup.usermanagementservice.repository;

import com.mygroup.usermanagementservice.model.User;

import java.sql.*;
import java.util.List;

public class UserRepository {
    private String databaseUrl;
    private String user;
    private String password;
    private String driverClass;

    public UserRepository(String databaseUrl, String user, String password, String driverClass) {
        this.databaseUrl = databaseUrl;
        this.user = user;
        this.password = password;
        this.driverClass = driverClass;
    }

    private ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(databaseUrl, user, password);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        connection.close();
        return rs;
    }

    public User addUser(User user) throws ClassNotFoundException, SQLException {
        String query = """
                INSERT INTO `users`
                VALUES(
                """ + user.getPesel() +
                ", " + user.getFirstName() +
                ", " + user.getLastName() +
                ", " + user.getCity() +
                ", " + user.getPostCode() +
                ", " + user.getEmail()
                + ");";
        ResultSet rs = executeQuery(query);
        rs.close();
        return null;
    }

    public List<User> getUsers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM `users`;";
        ResultSet rs = executeQuery(query);
        rs.close();
        return null;
    }

    public User deleteUser(Long pesel) throws SQLException, ClassNotFoundException {
        String query = """
                        DELETE * FROM `users`
                        WHERE pesel =
                """ + pesel + ";";
        ResultSet rs = executeQuery(query);
        rs.close();
        return null;
    }
}
