package controllers;

import models.Login;
import models.Password;
import models.User;
import services.Users.UsersService;

public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    public User getUser(Login login, Password password) {
        return usersService.getUser(login, password);
    }

    public void createUser(String name, String surname, Login login, Password password) {
        usersService.createUser(name, surname, login, password);
    }

    public void saveUsers() {
        usersService.saveUsers();
    }

}
