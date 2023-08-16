package services.Booking;

import models.Booking;

import java.util.Set;

public interface BookingService {
    Set<Booking> getAll();
    Booking getBookingById(int ID);
    void create(Booking book);
    boolean cancel(int ID);
    boolean cancel(Booking booking);
}
