package data;

import models.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataStorage {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<Vehicle> vehicles;
    private List<Client> clients;
    private List<Repair> repairs;
    private Map<String, Part> parts;
    private List<User> users;

    private String vehicleFilename = "vehicles.txt";
    private String clientFilename = "clients.txt";
    private String repairFilename = "repairs.txt";
    private String partFilename = "parts.txt";
    private String userFilename = "users.txt";

    public DataStorage() {
        vehicles = new ArrayList<>();
        clients = new ArrayList<>();
        repairs = new ArrayList<>();
        parts = new HashMap<>();
        users = new ArrayList<>();
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
    public List<Client> getClients() {
        return clients;
    }
    public List<Repair> getRepairs() {
        return repairs;
    }
    public Map<String, Part> getParts() {
        return parts;
    }
    public List<User> getUsers() {
        return users;
    }
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    public void addClient(Client client) {
        clients.add(client);
    }
    public void addRepair(Repair repair) {
        repairs.add(repair);
    }
    public void addPart(Part part) {
        parts.put(part.getName(), part);
    }
    public void addUser(User user) {
        users.add(user);
    }
    public static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
    private void saveVehiclesToTextFile() {
        try (PrintWriter writer = new PrintWriter(vehicleFilename)) {
            for (Vehicle vehicle : vehicles) {
                writer.println(vehicle.getType() + "," + vehicle.getRegistrationNumber() + "," + vehicle.getOwner());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveRepairsToTextFile() {
        try (PrintWriter writer = new PrintWriter(repairFilename)) {
            for (Repair repair : repairs) {
                String formattedDate = dateFormat.format(repair.getRepairDate());
                writer.println(repair.getDescription() + "," + repair.getCost() + "," + repair.getVehicleRegistrationNumber() + "," + formattedDate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveUsersToTextFile() {
        try (PrintWriter writer = new PrintWriter(userFilename)) {
            for (User user : users) {
                if (user instanceof Client) {
                    String role = user.getRole();
                    String level = ((Client) user).getLevel();
                    writer.println(user.getUsername() + "," + user.getPassword() + "," + role + "," + level);
                } else {
                    String role = user.getRole();
                    writer.println(user.getUsername() + "," + user.getPassword() + "," + role);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadVehiclesFromTextFile() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(vehicleFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 0) {
                    String type = parts[0];
                    String registrationNumber = parts[1];
                    String owner = parts[2];
                    vehicles.add(new Vehicle(type, registrationNumber, owner));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadRepairsFromTextFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(repairFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String vehicleRegistrationNumber = parts[2];
                    Date repairDate = dateFormat.parse(parts[3]);
                    String description = parts[0];
                    double cost = Double.parseDouble(parts[1]);
                    repairs.add(new Repair(vehicleRegistrationNumber, repairDate, description, cost));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    private void loadUsersFromTextFile() throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String username = parts[0];
                    String password = parts[1];
                    String role = parts[2];
                    User user = null;
                    if (role.equalsIgnoreCase("client") && parts.length >= 4) {
                        String level = parts[3];
                        user = new Client(username, password, level);
                    } else if (role.equalsIgnoreCase("technician")) {
                        user = new Technician(username, password);
                    } else if (role.equalsIgnoreCase("staff")) {
                        user = new Staff(username, password);
                    } else if (role.equalsIgnoreCase("admin")) {
                        user = new Admin(username, password);
                    }
                    if (user != null) {
                        users.add(user);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadAllDataFromTextFiles() throws FileNotFoundException {
        loadVehiclesFromTextFile();
        loadRepairsFromTextFile();
        loadUsersFromTextFile();
    }
    public void saveAllDataToTextFiles() {
        saveVehiclesToTextFile();
        saveRepairsToTextFile();
        saveUsersToTextFile();
    }
}
