package com.mygroup.usermanagementservice.repository;

import com.mygroup.usermanagementservice.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private String databaseUrl;
    private String userName;
    private String password;
    private String driverClass;

    public UserRepository(String databaseUrl, String userName, String password, String driverClass) {
        this.databaseUrl = databaseUrl;
        this.userName = userName;
        this.password = password;
        this.driverClass = driverClass;
    }

    private ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(databaseUrl, userName, password);
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
        return user;
    }

    public List<User> getUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM `users`;";
        ResultSet rs = executeQuery(query);
        while (rs.next()) {
            Long pesel = rs.getLong("pesel");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String postCode = rs.getString("post_code");
            String city = rs.getString("city");
            String email = rs.getString("email");

            User user = new User(pesel, firstName, lastName, postCode, city, email);
            users.add(user);
        }

        rs.close();
        return users;
    }

    public void deleteUser(Long pesel) throws SQLException, ClassNotFoundException {
        String query = """
                        DELETE * FROM `users`
                        WHERE pesel =
                """ + pesel + ";";
        ResultSet rs = executeQuery(query);
        rs.close();
    }
}
