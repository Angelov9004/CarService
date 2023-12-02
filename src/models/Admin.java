package models;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "admin");
    }
    @Override
    public boolean authenticate(String enteredPassword) {
        return getPassword().equals(enteredPassword);
    }
}
