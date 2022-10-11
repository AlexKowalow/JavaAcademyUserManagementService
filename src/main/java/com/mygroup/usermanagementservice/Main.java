package com.mygroup.usermanagementservice;

import com.mygroup.usermanagementservice.model.User;
import com.mygroup.usermanagementservice.repository.UserRepository;
import com.mygroup.usermanagementservice.service.MailService;
import com.mygroup.usermanagementservice.service.UserService;
import jakarta.mail.MessagingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException, SQLException, ClassNotFoundException {
        System.out.println("==============================================");
        System.out.println("|   WELCOME TO THE USER MANAGEMENT SERVICE   |");
        System.out.println("==============================================");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        Logger logger = LogManager.getLogger();

        String host = "smtp.mailtrap.io";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "2525");

        MailService mailService = new MailService(properties, "c2ef9b2375192f", "0459524773a6e2");

        UserRepository userRepository = new UserRepository("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "root", "com.mysql.cj.jdbc.Driver");
        UserService userService = new UserService(userRepository);

        try {
            while (true) {
                System.out.println("==============================================");
                System.out.println("|                OPTION SELECT               |");
                System.out.println("==============================================");
                System.out.println("|   ADD USER                             1   |");
                System.out.println("|   DELETE USER                          2   |");
                System.out.println("|   EXIT                                 3   |");
                System.out.println("==============================================");
                System.out.print("> ");

                int opt = Integer.parseInt(sc.nextLine());

                boolean exit = false;
                switch (opt) {
                    case 1 -> {
                        User user = userService.addUser();
                        mailService.sendEmail("hajoki4933@migonom.com", "USER ACTIONS", "USER CREATED: " + user);
                    }
                    case 2 -> {
                        User user = userService.deleteUser();
                        mailService.sendEmail("hajoki4933@migonom.com", "USER ACTIONS", "USER DELETED: " + user);
                    }
                    case 3 -> exit = true;
                    default -> throw new IOException("Unknown option");
                }

                if (exit) {
                    break;
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
