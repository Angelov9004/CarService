package menus;

import models.User;
import services.*;

import java.util.List;
import java.util.Scanner;

public class UserManagementMenu {
    public static void displayMenu(Scanner scanner, UserManagementService userManagementService, RegistrationService registrationService) {
        while (true) {
            System.out.println("\nUser Management Menu");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. View All Users");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");
            int userManagementChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userManagementChoice) {
                case 1:
                    registrationService.registerUser(scanner);
                    break;
                case 2:

                    System.out.println("Enter the username of the user to update:");
                    String usernameToUpdate = scanner.nextLine();
                    User userToUpdate = userManagementService.getUserByUsername(usernameToUpdate);
                    if (userToUpdate != null) {
                        System.out.println("Enter the new username (leave empty to keep the same):");
                        String newUsername = scanner.nextLine();
                        if (!newUsername.isEmpty()) {
                            userToUpdate.setUsername(newUsername);
                        }
                        System.out.println("Enter the new password (leave empty to keep the same):");
                        String newPassword = scanner.nextLine();
                        if (!newPassword.isEmpty()) {
                            userToUpdate.setPassword(newPassword);
                        }
                        userManagementService.updateUser(userToUpdate);
                        System.out.println("User updated successfully!");
                    } else {
                        System.out.println("User not found. Update failed.");
                    }
                    break;

                case 3:
                    System.out.println("Enter the username of the user to delete:");
                    String usernameToDelete = scanner.nextLine();
                    User userToDelete = userManagementService.getUserByUsername(usernameToDelete);
                    if (userToDelete != null) {
                        userManagementService.deleteUser(userToDelete);
                        System.out.println("User deleted successfully!");
                    } else {
                        System.out.println("User not found. Deletion failed.");
                    }
                    break;
                case 4:
                    List<User> allUsers = userManagementService.getAllUsers();
                    if (allUsers.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        System.out.println("List of All Users:");
                        for (User user : allUsers) {
                            System.out.println(user.getUsername());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Returning to previous menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
