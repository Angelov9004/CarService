package services;

import models.Vehicle;
import data.DataStorage;

import java.io.FileNotFoundException;
import java.util.List;

public class VehicleService {
    private DataStorage dataStorage;
    public VehicleService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    public void addVehicle(Vehicle vehicle) {
        dataStorage.addVehicle(vehicle);
    }
    public List<Vehicle> getAllVehicles() throws FileNotFoundException {
        return dataStorage.getVehicles();
    }
    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        return dataStorage.getVehicles().stream()
                .filter(vehicle -> vehicle.getRegistrationNumber().equals(registrationNumber))
                .findFirst()
                .orElse(null);
    }
}
