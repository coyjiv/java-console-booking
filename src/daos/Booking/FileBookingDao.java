package daos.Booking;

import daos.Session.SessionDao;
import daos.Users.UsersDao;
import models.Booking;
import models.Flight;

import java.io.*;
import java.util.*;

public class FileBookingDao implements BookingDao {

    SessionDao sessionDao;

    UsersDao usersDao;

    public FileBookingDao(SessionDao sessionDao, UsersDao usersDao) {
        this.sessionDao = sessionDao;
        this.usersDao = usersDao;
    }

    @Override
    public Set<Booking> getAll() {
        return usersDao.getAllBookings();
    }

    @Override
    public Booking getBookingById(int ID) throws NoSuchElementException {
        Optional<Booking> book = getAll().stream().filter(booking -> booking.getID() == ID).findFirst();

        if (book.isPresent()) {
            return (Booking) book.get();
        } else {
            throw new NoSuchElementException("Бронювання за таким ID не існує !");
        }
    }

    @Override
    public void create(Booking book) {
        sessionDao.getSession().getUser().getBookings().add(book);
        usersDao.saveUsers();
    }

    @Override
    public boolean cancel(int ID) {
        boolean result = sessionDao.getSession().getUser().getBookings().remove(getBookingById(ID));
        if (result) {
            usersDao.saveUsers();
        }
        return result;
    }

    @Override
    public boolean cancel(Booking booking) {
        boolean result = sessionDao.getSession().getUser().getBookings().remove(booking);
        if (result) {
            usersDao.saveUsers();
        }
        return result;
    }
}