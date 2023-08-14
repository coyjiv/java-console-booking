package services.Session;

import controllers.UsersController;
import daos.Session.SessionDao;
import daos.Users.UsersDao;
import models.Login;
import models.Password;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Users.UsersService;

import static org.junit.jupiter.api.Assertions.*;

public class TestSessionService {
    SessionDao sessionDao;
    SessionService sessionService;
    UsersDao usersDao;
    UsersService usersService;
    UsersController usersController;

    @BeforeEach
    public void beforeEach() {
        usersDao = new UsersDao();
        usersService = new UsersService(usersDao);
        usersController = new UsersController(usersService);
        sessionDao = new SessionDao();
        sessionService = new SessionService(sessionDao, usersController);
    }

    @Test
    public void testRegistration() {
        int oldCount = usersDao.getAllUsers().size();

        Login login = new Login("TestLogin");
        Password password = new Password("TestPassword");


        if (sessionService.registration("TestName", "TestSurname", login, password)) {
            assertEquals(oldCount + 1, usersDao.getAllUsers().size());
        } else {
            assertEquals(oldCount, usersDao.getAllUsers().size());
        }
    }

    @Test
    public void testLogin() {
        Login login1 = new Login("TestLogin1");
        Password password1 = new Password("TestPassword1");

        sessionService.registration("TestName", "TestSurname", login1, password1);

        assertTrue(sessionService.login(login1, password1));

        assertEquals(sessionDao.getSession().getUser(), usersDao.getUser(login1, password1));

    }

    @Test
    public void testLogout() {
        sessionService.logout();

        assertEquals(sessionService.getSession().getUser(), null);
    }
}