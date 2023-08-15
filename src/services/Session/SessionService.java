package services.Session;

import daos.Session.SessionDao;
import models.Login;
import models.Password;
import models.Session;
import services.Users.UsersService;
import utils.ConsoleColors;

public class SessionService implements ISessionService, ConsoleColors {

    private final SessionDao sessionDao;

    UsersService usersService;

    public SessionService(SessionDao dao,  UsersService usersController) {
        this.sessionDao = dao;
        this.usersService = usersController;
    }

    public Session getSession() {
        return sessionDao.getSession();
    }

    public boolean login(Login login, Password password) {
        if (usersService.getUser(login, password) != null) {
            sessionDao.getSession().setUser(usersService.getUser(login, password));
            return true;
        }
        return false;
    }

    public boolean registration(String name, String surname, Login login, Password password) {

        if (usersService.getUser(login, password) != null) {
            return false;
        }

        usersService.createUser(name, surname, login, password);

        sessionDao.getSession().setUser(usersService.getUser(login, password));

        usersService.saveUsers();

        return true;
    }

    public void logout() {
        sessionDao.logout();
    }
}