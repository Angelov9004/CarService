package menus;

import models.Client;
import models.Repair;
import models.User;
import models.Vehicle;
import services.RepairService;
import services.VehicleService;
import data.DataStorage;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClientMenu {
    public static void displayMenu(Scanner scanner, User user, VehicleService vehicleService, RepairService repairService) throws FileNotFoundException {
        while (true) {
            System.out.println("\nClient Menu");
            System.out.println("1. View My Vehicles");
            System.out.println("2. View Repairs and Payments");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int clientChoice = scanner.nextInt();
            scanner.nextLine();

            switch (clientChoice) {
                case 1:
                    String clientUsername = user.getUsername();
                    for (Vehicle vehicle : vehicleService.getAllVehicles()) {
                        if (vehicle.getOwner().equals(clientUsername)) {
                            System.out.println("Vehicle Type: " + vehicle.getType());
                            System.out.println("Registration Number: " + vehicle.getRegistrationNumber());
                        }
                    }
                    break;
                case 2:
                    clientUsername = user.getUsername();
                    for (Vehicle vehicle : vehicleService.getAllVehicles()) {
                        if (clientUsername.equals(vehicle.getOwner())) {
                            System.out.println("Vehicle: " + vehicle.getType() + " - " + vehicle.getRegistrationNumber());
                            System.out.println("Repairs:");
                            for (Repair repair : repairService.getAllRepairs()) {
                                if (vehicle.getRegistrationNumber().equals(repair.getVehicleRegistrationNumber())) {
                                    double repairCost = repair.getCost();
                                    String clientLevel = ((Client) user).getLevel();
                                    if (clientLevel.equals("silver")) {
                                        repairCost *= 1.2;
                                        repair.setCost(repairCost);
                                    } else if (clientLevel.equals("gold")) {
                                        repairCost *= 1.1;
                                        repair.setCost(repairCost);
                                    }
                                    System.out.println(" - Description: " + repair.getDescription());
                                    System.out.println(" - Cost: " + repair.getCost());
                                    System.out.println(" - Repair Date: " + repair.getRepairDate());
                                }
                            }
                        }
                    }
                    double totalPayments = getTotalPayments(vehicleService, repairService, clientUsername);
                    System.out.println("Total Payments: " + totalPayments);
                    break;
                case 3:
                    System.out.println("Exiting the Client menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static double getTotalPayments(VehicleService vehicleService, RepairService repairService, String clientUsername) throws FileNotFoundException {
        double totalPayments = 0.0;
        for (Vehicle vehicle : vehicleService.getAllVehicles()) {
            if (clientUsername.equals(vehicle.getOwner())) {
                for (Repair repair : repairService.getAllRepairs()) {
                    if (vehicle.getRegistrationNumber().equals(repair.getVehicleRegistrationNumber())) {
                        totalPayments += repair.getCost();
                    }
                }
            }
        }
        return totalPayments;
    }
}