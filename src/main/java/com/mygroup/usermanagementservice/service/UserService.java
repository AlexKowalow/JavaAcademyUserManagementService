package com.mygroup.usermanagementservice.service;

import com.mygroup.usermanagementservice.model.User;
import com.mygroup.usermanagementservice.repository.UserRepository;
import com.mygroup.usermanagementservice.util.RegexVerifier;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private String adminEmail;
    private String dataBaseURL;
    private String dataBaseUser;
    private String dataBasePassword;
    private String dataBAseDriverClass;

    private UserRepository userRepository;

    public UserService(
            String adminEmail,
            String dataBaseURL,
            String dataBaseUser,
            String dataBasePassword,
            String dataBAseDriverClass) {
        this.adminEmail = adminEmail;
        this.dataBaseURL = dataBaseURL;
        this.dataBaseUser = dataBaseUser;
        this.dataBasePassword = dataBasePassword;
        this.dataBAseDriverClass = dataBAseDriverClass;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser() throws IOException {
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

        if (!RegexVerifier.verifyArgument(pesel, "^$")) {
            throw new IOException("Invalid PESEL");
        }

        if (!RegexVerifier.verifyArgument(email, "^$")) {
            throw new IOException("Invalid email");
        }

        List<User> users = userRepository.getUsers();
        boolean peselIsBusy = users.stream().anyMatch(u -> u.getPesel().equals(Long.parseLong(pesel)));

        if (peselIsBusy) {
            throw new IOException("PESEL must be unique");
        }

        User user = new User(Long.parseLong(pesel), firstName, lastName, postCode, city, email);
        user = userRepository.addUser(user);

        sendEmail("USER CREATED: " + user.toString());

        return user;
    }

    public User deleteUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===================");
        System.out.println("|   DELETE USER   |");
        System.out.println("===================");
        System.out.println();

        System.out.print("pesel: ");
        String pesel = sc.nextLine();

        User user = userRepository.deleteUser(Long.parseLong(pesel));
        sendEmail("USER DELETED: " + user.toString());
        return user;
    }

    private void sendEmail(String msg) {

    }
}
