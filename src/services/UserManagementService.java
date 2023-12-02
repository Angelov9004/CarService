package services;

import models.User;

import java.util.List;

public class UserManagementService {
    private List<User> users;
    public UserManagementService(List<User> users) {
        this.users = users;
    }
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(updatedUser.getUsername())) {
                user.setPassword(updatedUser.getPassword());
                user.setRole(updatedUser.getRole());
                break;
            }
        }
    }
    public void deleteUser(User user) {
        users.remove(user);
    }
    public List<User> getAllUsers() {
        return users;
    }
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
