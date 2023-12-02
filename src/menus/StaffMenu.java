package menus;

import models.User;
import models.Vehicle;
import services.*;


import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class StaffMenu {
    public static void displayMenu(Scanner scanner, User loggedUser, VehicleService vehicleService, UserService userService) throws FileNotFoundException {
        while (true) {
            System.out.println("\nStaff Menu");
            System.out.println("1. Take In Vehicles");
            System.out.println("2. Send Vehicles to Technicians");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int staffChoice = scanner.nextInt();
            scanner.nextLine();

            switch (staffChoice) {
                case 1:
                    // Display available client usernames
                    List<User> clients = userService.getAllUsers();
                    System.out.println("Available Clients:");
                    for (User client : clients) {
                        if (client.getRole().equalsIgnoreCase("client")) {
                            System.out.println("Username: " + client.getUsername());
                        }
                    }

                    System.out.println("Enter client's username (owner): "); // Prompt for client's username
                    String clientUsername = scanner.nextLine();
                    System.out.println("Enter vehicle type: ");
                    String type = scanner.nextLine();
                    System.out.println("Enter vehicle registration number: ");
                    String registrationNumber = scanner.nextLine();
                    Vehicle newVehicle = new Vehicle(type, registrationNumber, clientUsername); // Assign the vehicle to the client
                    vehicleService.addVehicle(newVehicle);
                    System.out.println("Vehicle taken in successfully!");
                    break;
                case 2:
                    System.out.println("Available Vehicles:");
                    List<Vehicle> vehicles = vehicleService.getAllVehicles();
                    for (Vehicle vehicle : vehicles) {
                        System.out.println("Registration Number: " + vehicle.getRegistrationNumber());
                        System.out.println("Type: " + vehicle.getType());
                        System.out.println("Owner: " + vehicle.getOwner());
                        System.out.println();
                    }
                    System.out.println("Enter the registration number of the vehicle to assign: ");
                    String vehicleRegistrationNumber = scanner.nextLine();
                    Vehicle selectedVehicle = vehicleService.getVehicleByRegistrationNumber(vehicleRegistrationNumber);
                    if (selectedVehicle == null) {
                        System.out.println("Vehicle not found. Assignment failed.");
                        break;
                    }
                    System.out.println("Available Technicians:");
                    List<User> users = userService.getAllUsers();
                    for (User user : users) {
                        if (user.getRole().equalsIgnoreCase("technician")) {
                            System.out.println("Name: " + user.getUsername());
                        }
                    }
                    System.out.println("Enter the username of the technician to assign: ");
                    String technicianUsername = scanner.nextLine();
                    User selectedTechnician = userService.getUserByUsername(technicianUsername);
                    if (selectedTechnician == null || !selectedTechnician.getRole().equalsIgnoreCase("technician")) {
                        System.out.println("Technician not found. Assignment failed.");
                        break;
                    }
                    selectedVehicle.setAssignedTechnician(selectedTechnician.getUsername());
                    System.out.println("Vehicle assigned to the technician successfully!");
                    break;
                case 3:
                    System.out.println("Exiting the Staff menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
