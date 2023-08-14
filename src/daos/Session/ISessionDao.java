package daos.Session;

import models.Session;

public interface ISessionDao {

    public Session getSession();

    public void setSession(Session session);

    public void logout();

}
