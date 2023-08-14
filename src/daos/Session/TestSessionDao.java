package daos.Session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSessionDao {
SessionDao sessionDao = new SessionDao();
@Test
    public void testLogout(){
    sessionDao.logout();
    Assertions.assertEquals(null,sessionDao.getSession().getUser());
}
}