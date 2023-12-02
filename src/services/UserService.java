package services;

import java.util.List;
import java.util.ArrayList;

import models.User;
import data.DataStorage;

public class UserService {
    private DataStorage dataStorage;

    public UserService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void addUser(User user) {
        dataStorage.addUser(user);
    }

    public User getUserByUsername(String username) {
        return dataStorage.getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return dataStorage.getUsers();
    }

    public void updateUser(User updatedUser) {
        List<User> users = dataStorage.getUsers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
    }

    public void deleteUser(User userToDelete) {
        dataStorage.getUsers().removeIf(user -> user.getUsername().equals(userToDelete.getUsername()));
    }
}
