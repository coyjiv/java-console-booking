package daos.Booking;

import models.Booking;

import java.util.*;

public class SetBookingDao implements BookingDao{
    private final Set<Booking> bookings = new HashSet<>();
    @Override
    public Set<Booking> getAll() {
        return this.bookings;
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
    public void create(Booking book) {
        bookings.add(book);
    }
    @Override
    public void cancel(int ID) {
        this.bookings.remove(getBookingById(ID));
    }
}
