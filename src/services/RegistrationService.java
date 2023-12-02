package services;

import data.DataStorage;
import models.Admin;
import models.Client;
import models.Staff;
import models.Technician;
import models.User;

import java.util.Scanner;

public class RegistrationService {
    private DataStorage dataStorage;
    private UserService userService;

    public RegistrationService(DataStorage dataStorage, UserService userService) {
        this.dataStorage = dataStorage;
        this.userService = userService;
    }

    public void registerUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        User existingUser = dataStorage.getUserByUsername(username);
        if (existingUser != null) {
            System.out.println("A user with the same username already exists.");
        } else {
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Select user role:");
            System.out.println("1. Admin");
            System.out.println("2. Technician");
            System.out.println("3. Client");
            System.out.println("4. Staff");
            int roleOption = Integer.parseInt(scanner.nextLine());
            User newUser = null;
            switch (roleOption) {
                case 1:
                    newUser = new Admin(username, password);
                    break;
                case 2:
                    newUser = new Technician(username, password);
                    break;
                case 3:
                    System.out.println("Select client level:");
                    System.out.println("1. Silver");
                    System.out.println("2. Gold");
                    System.out.println("3. Premium");
                    int levelOption = Integer.parseInt(scanner.nextLine());
                    switch (levelOption) {
                        case 1:
                            newUser = new Client(username, password, "silver");
                            break;
                        case 2:
                            newUser = new Client(username, password, "gold");
                            break;
                        case 3:
                            newUser = new Client(username, password, "premium");
                            break;
                    }
                    break;
                case 4:
                    newUser = new Staff(username, password);
                    break;
                default:
                    System.out.println("Invalid role option. User not created.");
                    return;
            }
            dataStorage.addUser(newUser);
            System.out.println("User added successfully!");
        }
    }
}
