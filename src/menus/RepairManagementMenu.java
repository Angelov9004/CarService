package menus;

import data.DataStorage;
import models.Repair;
import models.Vehicle;
import services.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RepairManagementMenu {
    public static void displayMenu(Scanner scanner, RepairService repairService, VehicleService vehicleService, DataStorage dataStorage) {
        while (true) {
            System.out.println("\nRepair Management Menu");
            System.out.println("1. Add Repair");
            System.out.println("2. Update Repair");
            System.out.println("3. Delete Repair");
            System.out.println("4. View All Repairs");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");
            int repairManagementChoice = scanner.nextInt();
            scanner.nextLine();

            switch (repairManagementChoice) {
                case 1:
                    System.out.print("Enter vehicle registration number: ");
                    String registrationNumber = scanner.nextLine();
                    Vehicle vehicle = vehicleService.getVehicleByRegistrationNumber(registrationNumber);
                    if (vehicle == null) {
                        System.out.println("Vehicle not found. Unable to add repair.");
                    } else {
                        System.out.print("Enter repair description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter repair cost: ");
                        double cost = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter repair date (yyyy-MM-dd): ");
                        String repairDateStr = scanner.nextLine();
                        Date repairDate = dataStorage.parseDate(repairDateStr);
                        if (repairDate != null) {
                            Repair newRepair = new Repair(registrationNumber, repairDate, description, cost);
                            repairService.addRepair(newRepair);
                            System.out.println("Repair added successfully!");
                        } else {
                            System.out.println("Invalid date format. Repair not added.");
                        }
                    }
                    break;
                case 2:

                    System.out.println("Enter the ID of the repair to update:");
                    int repairIdToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Repair repairToUpdate = repairService.getRepairById(repairIdToUpdate);
                    if (repairToUpdate != null) {
                        System.out.println("Current Repair Information:");
                        System.out.println("Description: " + repairToUpdate.getDescription());
                        System.out.println("Cost: " + repairToUpdate.getCost());
                        System.out.println("Repair Date: " + repairToUpdate.getRepairDate());
                        System.out.print("Enter new description (or press Enter to keep current): ");
                        String newDescription = scanner.nextLine();
                        if (!newDescription.isEmpty()) {
                            repairToUpdate.setDescription(newDescription);
                        }
                        System.out.print("Enter new cost (or press Enter to keep current): ");
                        String costStr = scanner.nextLine();
                        if (!costStr.isEmpty()) {
                            double newCost = Double.parseDouble(costStr);
                            repairToUpdate.setCost(newCost);
                        }
                        System.out.print("Enter new repair date (yyyy-MM-dd) (or press Enter to keep current): ");
                        String repairDateStr = scanner.nextLine();
                        if (!repairDateStr.isEmpty()) {
                            Date newRepairDate = dataStorage.parseDate(repairDateStr);
                            if (newRepairDate != null) {
                                repairToUpdate.setRepairDate(newRepairDate);
                            } else {
                                System.out.println("Invalid date format. The repair date remains unchanged.");
                            }
                        }
                        System.out.println("Repair updated successfully!");
                    } else {
                        System.out.println("Repair not found. Update failed.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the ID of the repair to delete:");
                    int repairIdToDelete = scanner.nextInt();
                    scanner.nextLine();
                    Repair repairToDelete = repairService.getRepairById(repairIdToDelete);
                    if (repairToDelete != null) {
                        repairService.deleteRepair(repairToDelete);
                        System.out.println("Repair deleted successfully!");
                    } else {
                        System.out.println("Repair not found. Deletion failed.");
                    }
                    break;
                case 4:
                    List<Repair> allRepairs = repairService.getAllRepairs();
                    if (allRepairs.isEmpty()) {
                        System.out.println("No repairs found.");
                    } else {
                        System.out.println("List of All Repairs:");
                        for (Repair repair : allRepairs) {
                            System.out.println("Repair ID: " + repair.getId());
                            System.out.println("Vehicle Registration Number: " + repair.getVehicleRegistrationNumber());
                            System.out.println("Repair Date: " + repair.getRepairDate());
                            System.out.println("Description: " + repair.getDescription());
                            System.out.println("Cost: " + repair.getCost());
                            System.out.println();
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
