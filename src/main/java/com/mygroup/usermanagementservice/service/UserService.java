package com.mygroup.usermanagementservice.service;

import com.mygroup.usermanagementservice.model.User;
import com.mygroup.usermanagementservice.repository.UserRepository;
import com.mygroup.usermanagementservice.util.RegexVerifier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    //  jdbc:mysql://localhost:3306/test?useSSL=false
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser() throws IOException, ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("================");
        System.out.println("|   ADD USER   |");
        System.out.println("================");
        System.out.println();

        System.out.print("first_name: ");
        String firstName = sc.nextLine();
        System.out.print("last_name: ");
        String lastName = sc.nextLine();
        System.out.print("PESEL: ");
        String pesel = sc.nextLine();
        System.out.print("post_code: ");
        String postCode = sc.nextLine();
        System.out.print("city: ");
        String city = sc.nextLine();
        System.out.print("email: ");
        String email = sc.nextLine();

        if (!RegexVerifier.verifyArgument(pesel, "^[0-9]{2}((0[1-9])|(1[1-2]))(([0-2][1-9])|(3[0-1]))[0-9]{5}$")) {
            throw new IOException("Invalid PESEL");
        }

        if (!RegexVerifier.verifyArgument(email, "^[A-z._]*@{1}[A-z]*\\.[A-z]*$")) {
            throw new IOException("Invalid email");
        }

        List<User> users = userRepository.getUsers();
        boolean peselIsBusy = users.stream().anyMatch(u -> u.getPesel().equals(Long.parseLong(pesel)));

        if (peselIsBusy) {
            throw new IOException("PESEL must be unique");
        }

        User user = new User(Long.parseLong(pesel), firstName, lastName, postCode, city, email);
        user = userRepository.addUser(user);

        return user;
    }

    public User deleteUser() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("===================");
        System.out.println("|   DELETE USER   |");
        System.out.println("===================");
        System.out.println();

        System.out.print("pesel: ");
        String pesel = sc.nextLine();

        User user = userRepository.getUsers().stream().filter(u -> u.getPesel().equals(Long.parseLong(pesel))).findFirst().orElse(null);
        userRepository.deleteUser(Long.parseLong(pesel));
        return user;
    }
}
