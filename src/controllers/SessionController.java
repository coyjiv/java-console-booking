package controllers;

import models.Login;
import models.Password;
import models.Session;
import services.Session.SessionsService;
import utils.ConsoleColors;
import utils.Logger;

public class SessionController implements ConsoleColors {
    private final SessionsService sessionsService;
    private final UsersController usersController;
    private Session session = new Session(null);

    public SessionController(SessionsService sessionsService, UsersController usersController) {
        this.sessionsService = sessionsService;
        this.usersController = usersController;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void authentication(Login login, Password password) {
        if (usersController.getUser(login, password) != null) {
            Logger.notCorrectInput(YELLOW_BOLD + "Користувача знайдено! Ви авторизовані!" + RESET);
            session.setUser(usersController.getUser(login, password));
        } else {
            Logger.notCorrectInput(YELLOW_BOLD + "Користувача не знайдено, створено нового, ви авторизовані!" + RESET);
            usersController.createUser(login, password);
            session.setUser(usersController.getUser(login, password));
        }
    }

    public void logout() {
        session.setUser(null);
    }
}