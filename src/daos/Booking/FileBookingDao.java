package daos.Booking;

import daos.Session.SessionDao;
import daos.Users.UsersDao;
import models.Booking;
import models.Flight;
import utils.Logger;

import java.io.*;
import java.util.*;

import static utils.ConsoleColors.RED_BRIGHT;
import static utils.ConsoleColors.RESET;

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
    public Booking getBookingById(int ID) {
        Optional<Booking> book = getAll().stream().filter(booking -> booking.getID() == ID).findFirst();

        if (book.isPresent()) {
            return (Booking) book.get();
        } else {
            Logger.notCorrectInput(RED_BRIGHT+"Бронювання за таким ID не існує !"+RESET);
            return null;
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