package menus;

import models.Part;
import models.Repair;
import models.User;
import models.Vehicle;
import services.PartService;
import services.RepairService;
import services.VehicleService;
import data.DataStorage;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TechnicianMenu {
    public static void displayMenu(Scanner scanner, User user, PartService partService, RepairService repairService, VehicleService vehicleService) {
        while (true) {
            System.out.println("\nTechnician Menu");
            System.out.println("1. Order Parts");
            System.out.println("2. Complete Repairs");
            System.out.println("3. Create Repair Order");
            System.out.println("4. View Repairs");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int technicianChoice = scanner.nextInt();
            scanner.nextLine();

            switch (technicianChoice) {
                case 1:
                    System.out.println("Enter the name of the part to order: ");
                    String partName = scanner.nextLine();
                    System.out.println("Enter the price of the part: ");
                    double partPrice = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter the quantity to order: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    Part orderedPart = new Part(partName, partPrice, quantity);
                    partService.addPart(orderedPart);
                    System.out.println("Parts ordered successfully.");
                    break;
                case 2:
                    System.out.println("Enter the ID of the repair to mark as completed:");
                    int repairIdToComplete = scanner.nextInt();
                    scanner.nextLine();
                    Repair repairToComplete = repairService.getRepairById(repairIdToComplete);
                    if (repairToComplete != null) {
                        repairToComplete.setCompleted(true);
                        repairService.updateRepair(repairToComplete);
                        System.out.println("Repair marked as completed successfully!");
                    } else {
                        System.out.println("Repair not found. Marking as completed failed.");
                    }
                    break;
                case 3:
                    System.out.println("Enter a brief description of the repair: ");
                    String repairDescription = scanner.nextLine();
                    System.out.println("Enter the vehicle registration number to assign the repair: ");
                    String vehicleRegistrationNumber = scanner.nextLine();
                    System.out.println("Enter the repair cost: ");
                    double repairCost = Double.parseDouble(scanner.nextLine());
                    Vehicle assignedVehicle = vehicleService.getVehicleByRegistrationNumber(vehicleRegistrationNumber);
                    if (assignedVehicle != null) {
                        Repair newRepair = new Repair(assignedVehicle.getRegistrationNumber(), new Date(), repairDescription, repairCost);
                        assignedVehicle.addRepair(newRepair);
                        repairService.addRepair(newRepair);
                        System.out.println("Repair order created successfully and assigned to the vehicle.");
                    } else {
                        System.out.println("Vehicle not found. Repair order creation failed.");
                    }
                    break;
                case 4:
                    List<Repair> repairs = repairService.getAllRepairs();
                    System.out.println("List of Repairs:");
                    for (Repair repair : repairs) {
                        System.out.println("Repair ID: " + repair.getId());
                        System.out.println("Description: " + repair.getDescription());
                        System.out.println("Cost: " + repair.getCost());
                        System.out.println("Vehicle Registration Number: " + repair.getVehicleRegistrationNumber());
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println("Exiting the Technician menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
