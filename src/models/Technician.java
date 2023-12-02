package models;

public class Technician extends User {
    public Technician(String username, String password) {
        super(username, password, "technician");
    }
    @Override
    public boolean authenticate(String enteredPassword) {
        return getPassword().equals(enteredPassword);
    }
}

