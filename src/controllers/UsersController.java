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
    public User getUser(Login login , Password password){
       return usersService.getUser(login,password);
    }
    public void createUser(Login login , Password password){
        usersService.createUser(login,password);
    }
}
