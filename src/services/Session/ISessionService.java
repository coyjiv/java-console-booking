package services.Session;

import models.Login;
import models.Password;

public interface ISessionService {
    public boolean login(Login login, Password password);

    public boolean registration(String name, String surname, Login login, Password password);
}
