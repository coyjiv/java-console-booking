package daos.Booking;

import models.Booking;

import java.util.Collection;
import java.util.Set;

public interface BookingDao {
    Set<Booking> getAll();
    Booking getBookingById(int ID);
    void create(Booking book);
    boolean cancel(int ID);
    boolean cancel(Booking booking);
}
