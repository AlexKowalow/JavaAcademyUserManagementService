package com.mygroup.usermanagementservice;

import com.mygroup.usermanagementservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("|   WELCOME TO THE USER MANAGEMENT SERVICE   |");
        System.out.println("==============================================");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        Logger logger = LogManager.getLogger();

        UserService userService = new UserService("", "", "", "", "");

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
                        userService.addUser();
                    }
                    case 2 -> {
                        userService.deleteUser();
                    }
                    case 3 -> {
                        exit = true;
                    }
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
