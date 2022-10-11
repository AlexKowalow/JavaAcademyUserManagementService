package com.mygroup.usermanagementservice.repository;

import com.mygroup.usermanagementservice.model.User;

import java.util.List;

public class UserRepository {
    private String databaseUrl;
    private String user;
    private String password;

    public UserRepository(String databaseUrl, String user, String password) {
        this.databaseUrl = databaseUrl;
        this.user = user;
        this.password = password;
    }

    public User addUser(User user) {
        return null;
    }

    public List<User> getUsers() {
        return null;
    }

    public User deleteUser(Long pesel) {
        return null;
    }

    private void sendEmail(String msg) {

    }
}
