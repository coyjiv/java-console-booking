package daos.Users;

import models.Login;
import models.Password;
import models.User;

public interface IUsersDao {
    public void loadUsers();

    public User getUser(Login login , Password password);

    public void createUser(String name, String surname,Login login , Password password);

    public void saveUsers();

}