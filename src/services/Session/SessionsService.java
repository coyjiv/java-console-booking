package services.Session;

import daos.Sessions.SessionsDao;

public class SessionsService {

    private final SessionsDao dao;

    public SessionsService(SessionsDao dao) {
        this.dao = dao;
    }



}