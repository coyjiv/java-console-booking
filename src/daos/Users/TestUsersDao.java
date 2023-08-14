package daos.Users;

import models.Login;
import models.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUsersDao {
    UsersDao usersDao;

    @BeforeEach
    public void beforeEach(){
        usersDao = new UsersDao();
        File file = new File("users.ser");
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void testCreateUser(){

        int oldCount = usersDao.getAllUsers().size();

        Login login = new Login("testLogin");
        Password password = new Password("testPassword");

        usersDao.createUser("TestName","TestSurname",login,password);

        int newCount = usersDao.getAllUsers().size();

        assertEquals(oldCount + 1,newCount);
    }
}
