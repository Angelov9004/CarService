package models;

public class Client extends User {
    private String name;
    private String level;
    public Client(String username, String password, String level) {

        super(username, password, "client");
        this.level=level;
    }
    public String getName() {
        return name;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    @Override
    public boolean authenticate(String enteredPassword) {
        return getPassword().equals(enteredPassword);
    }
}