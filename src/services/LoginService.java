package services;

import data.DataStorage;
import models.User;
import java.util.Scanner;

public class LoginService {
    private DataStorage dataStorage;
    public LoginService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    public User login(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        User user = dataStorage.getUserByUsername(username);
        if (user != null) {
            System.out.println("Enter password:");
            String enteredPassword = scanner.nextLine();
            if (isPasswordCorrect(enteredPassword, user.getPassword())) {
                System.out.println("Login successful!");
                return user;
            } else {
                System.out.println("Invalid password. Login failed.");
            }
        } else {
            System.out.println("User not found. Login failed.");
        }
        return null;
    }
    private boolean isPasswordCorrect(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);
    }
}
