package services.Session;

import controllers.UsersController;
import daos.Session.SessionDao;
import models.Login;
import models.Password;
import models.Session;
import utils.ConsoleColors;

public class SessionService implements ISessionService, ConsoleColors {

    private final SessionDao sessionDao;

    UsersController usersController;

    public SessionService(SessionDao dao, UsersController usersController) {
        this.sessionDao = dao;
        this.usersController = usersController;
    }

    public Session getSession() {
        return sessionDao.getSession();
    }

    public boolean login(Login login, Password password) {
        if (usersController.getUser(login, password) != null) {
            sessionDao.getSession().setUser(usersController.getUser(login, password));
            return true;
        }
        return false;
    }

    public boolean registration(String name, String surname, Login login, Password password) {

        if (usersController.getUser(login, password) != null) {
            return false;
        }

        usersController.createUser(name, surname, login, password);

        sessionDao.getSession().setUser(usersController.getUser(login, password));

        usersController.saveUsers();

        return true;
    }

    public void logout() {
        sessionDao.logout();
    }
}