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
    public void create(Booking book) {
        bookings.add(book);
    }
    @Override
    public void cancel(Booking book) {
        bookings.remove(book);
    }
}
