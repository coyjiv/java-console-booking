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
        return bookingDao.getAll();
    }

    @Override
    public Booking getBookingById(int ID) throws NoSuchElementException {
        Optional<Booking> book = getAll().stream().filter(booking -> booking.getID() == ID).findFirst();

        if (book.isPresent()){
            return (Booking) book.get();
        } else {
            throw new NoSuchElementException("Бронювання за таким ID не існує !");
        }
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
    public void cancel(Booking book) {
        bookingDao.cancel(book);
    }
}
