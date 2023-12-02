import data.DataStorage;
import services.*;
import models.*;
import menus.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DataStorage dataStorage = new DataStorage();
        UserService userService = new UserService(dataStorage);
        RepairService repairService = new RepairService(dataStorage);
        VehicleService vehicleService = new VehicleService(dataStorage);
        PartService partService = new PartService(dataStorage);
        Scanner scanner = new Scanner(System.in);
        RegistrationService registrationService = new RegistrationService(dataStorage, userService);
        try {
            dataStorage.loadAllDataFromTextFiles();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading data from text files: " + e.getMessage());
        }

        int choice;

        do {
            System.out.println("Auto Service Application");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Registration
                    registrationService.registerUser(scanner);
                    break;
                case 2:
                    // Login
                    LoginService loginService = new LoginService(dataStorage);
                    User loggedInUser = loginService.login(scanner);
                    if (loggedInUser != null) {
                        switch (loggedInUser.getRole().toLowerCase()) {
                            case "client":
                                ClientMenu.displayMenu(scanner, loggedInUser, vehicleService, repairService);
                                break;
                            case "technician":
                                TechnicianMenu.displayMenu(scanner, loggedInUser, partService, repairService, vehicleService);
                                break;
                            case "staff":
                                StaffMenu.displayMenu(scanner, loggedInUser, vehicleService, userService);
                                break;
                            case "admin":
                                UserManagementService userManagementService = new UserManagementService(dataStorage.getUsers());
                                RepairManagementService repairManagementService = new RepairManagementService(dataStorage.getRepairs());
                                AdminMenu.displayMenu(scanner, loggedInUser, userService, repairService, userManagementService, repairManagementService, vehicleService, dataStorage, registrationService);
                                break;
                            default:
                                System.out.println("Invalid user role.");
                                break;
                        }
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;
                case 3:
                    // Save data to text files before exiting
                    dataStorage.saveAllDataToTextFiles();
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }
}
