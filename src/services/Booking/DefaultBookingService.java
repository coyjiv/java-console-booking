package services.Booking;

import daos.Booking.BookingDao;
import models.Booking;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultBookingService implements BookingService{
    private final BookingDao bookingDao;

    public DefaultBookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public Set<Booking> getAll() {
        return this.bookingDao.getAll();
    }

    public Booking getBookingById(int ID) {
        return this.bookingDao.getBookingById(ID);
    }

    @Override
    public void create(Booking book) {
        bookingDao.create(book);
    }

    @Override
    public boolean cancel(int ID) {
        return bookingDao.cancel(ID);
    }

    @Override
    public boolean cancel(Booking booking) {
        return this.bookingDao.cancel(booking);
    }
}
