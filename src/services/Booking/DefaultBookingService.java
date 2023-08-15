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
    public Set<Booking> getAllPassengerBooks(String passenger) {
        Set<Booking> passengerBookings = getAll().stream()
                .filter(booking -> booking.getPassenger().equals(passenger))
                    .collect(Collectors.toCollection(HashSet::new));
        if (passengerBookings.size() > 0){
            return passengerBookings;
        } else {
            throw new NoSuchElementException("Жодного бронювання не було знайдено !");
        }
    }

    @Override
    public void create(Booking book) {
        bookingDao.create(book);
    }

    @Override
    public boolean cancel(int ID) {
        return this.bookingDao.cancel(ID);
    }

    @Override
    public boolean cancel(Booking booking) {
        return this.bookingDao.cancel(booking);
    }
}
