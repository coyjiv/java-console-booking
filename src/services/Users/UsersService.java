package services.Users;

import daos.Users.UsersDao;
import models.Login;
import models.Password;
import models.User;

public class UsersService {
private final UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public User getUser(Login login, Password password){
       return usersDao.getUser(login,password);
    }
    public void createUser(Login login , Password password){
        usersDao.createUser(login,password);
    }
}