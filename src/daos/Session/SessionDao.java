package daos.Session;

import models.Session;

public class SessionDao implements ISessionDao {
    private Session session = new Session(null);

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void logout() {
        session.setUser(null);
    }

}
