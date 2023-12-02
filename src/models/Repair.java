package models;

import java.util.Date;

public class Repair {
    private String vehicleRegistrationNumber;
    private Date repairDate;
    private String description;
    private static int idCounter = 0;
    private int id;
    private double cost;
    private boolean completed;

    public Repair(String vehicleRegistrationNumber, Date repairDate, String description, double cost) {
        this.id = ++idCounter;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.repairDate = repairDate;
        this.description = description;
        this.cost = cost;
        this.completed = false;
    }
    public int getId() {
        return id;
    }
    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }
    public Date getRepairDate() {
        return repairDate;
    }
    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
