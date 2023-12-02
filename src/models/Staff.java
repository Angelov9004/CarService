package models;

public class Staff extends User {
    public Staff(String username, String password) {
        super(username, password, "staff");
    }
    @Override
    public boolean authenticate(String enteredPassword) {
        return getPassword().equals(enteredPassword);
    }
}
