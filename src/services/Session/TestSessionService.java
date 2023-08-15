package services.Session;

import daos.Session.SessionDao;
import daos.Users.UsersDao;
import models.Login;
import models.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Users.UsersService;

import static org.junit.jupiter.api.Assertions.*;

public class TestSessionService {
    SessionDao sessionDao;
    SessionService sessionService;
    UsersDao usersDao;
    UsersService usersService;

    @BeforeEach
    public void beforeEach() {
        usersDao = new UsersDao();
        usersService = new UsersService(usersDao);
        sessionDao = new SessionDao();
        sessionService = new SessionService(sessionDao, usersService);
    }

    @Test
    public void testRegistration() {
        int oldCount = usersService.getAllUsers().size();

        Login login = new Login("TestLogin");
        Password password = new Password("TestPassword");


        if (sessionService.registration("TestName", "TestSurname", login, password)) {
            assertEquals(oldCount + 1, usersService.getAllUsers().size());
        } else {
            assertEquals(oldCount, usersService.getAllUsers().size());
        }
    }

    @Test
    public void testLogin() {
        Login login1 = new Login("TestLogin1");
        Password password1 = new Password("TestPassword1");

        sessionService.registration("TestName", "TestSurname", login1, password1);

        assertTrue(sessionService.login(login1, password1));

        assertEquals(sessionService.getSession().getUser(), usersService.getUser(login1, password1));

    }

    @Test
    public void testLogout() {
        sessionService.logout();

        assertEquals(sessionService.getSession().getUser(), null);
    }
}