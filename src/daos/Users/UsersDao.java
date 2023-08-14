package daos.Users;

import models.Login;
import models.Password;
import models.User;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class UsersDao implements IUsersDao {
    private final Set<User> users = new HashSet<>();

    private static final String USERS_FILE_NAME = "users.ser";

    public UsersDao() {
        loadUsers();
    }

    @Override
    public User getUser(Login login, Password password) {
        for (User user : users) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void createUser(Login login, Password password) {
        users.add(new User(login, password));
    }

    @Override
    public void loadUsers() {
        File file = new File(USERS_FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object readObject = ois.readObject();
                if (readObject instanceof Set) {
                    users.addAll((Set<User>) readObject);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}