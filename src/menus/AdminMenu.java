package menus;

import data.DataStorage;
import models.User;
import services.*;

import java.util.Scanner;

public class AdminMenu {
    public static void displayMenu(
            Scanner scanner,
            User user,
            UserService userService,
            RepairService repairService,
            UserManagementService userManagementService,
            RepairManagementService repairManagementService,
            VehicleService vehicleService,
            DataStorage dataStorage,
            RegistrationService registrationService) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. User Management");
            System.out.println("2. Repair Management");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1:
                    UserManagementMenu.displayMenu(scanner, userManagementService, registrationService);
                    break;
                case 2:
                    RepairManagementMenu.displayMenu(scanner, repairService, vehicleService, dataStorage);
                    break;
                case 3:
                    System.out.println("Exiting the Admin menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
