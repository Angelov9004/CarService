package models;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String type;
    private String registrationNumber;
    private String owner;
    private String assignedTechnician;
    private List<Repair> repairs;
    public Vehicle(String type, String registrationNumber, String owner) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.assignedTechnician = null;
        this.repairs = new ArrayList<>();
    }

    public String getType() {
        return type;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public String getOwner() {
        return owner;
    }
    public void addRepair(Repair repair) {
        repairs.add(repair);
    }
    public void setAssignedTechnician(String assignedTechnician) {
        this.assignedTechnician = assignedTechnician;
    }
}
