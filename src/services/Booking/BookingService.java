package services.Booking;

import models.Booking;

import java.util.Collection;
import java.util.Set;

public interface BookingService {
    Set<Booking> getAll();
    Booking getBookingById(int ID);
    Set<Booking> getAllPassengerBooks(String passenger);
    void create(Booking book);
    void cancel(int ID);
}
