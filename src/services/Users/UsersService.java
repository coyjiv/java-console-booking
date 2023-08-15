package services.Users;

import daos.Users.UsersDao;
import models.Booking;
import models.Login;
import models.Password;
import models.User;

import java.util.Set;

public class UsersService {
    private final UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public Set<User> getAllUsers() {
        return usersDao.getAllUsers();
    }

    public void addBook(User user, Booking book) {
        usersDao.addBook(user,book);
    }

    public void deleteBook(User user, Booking book) {
        usersDao.deleteBook(user,book);
    }

    public User getUser(Login login, Password password) {
        return usersDao.getUser(login, password);
    }

    public void createUser(String name, String surname, Login login, Password password) {
        usersDao.createUser(name, surname, login, password);
    }

    public void saveUsers() {
        usersDao.saveUsers();
    }

}