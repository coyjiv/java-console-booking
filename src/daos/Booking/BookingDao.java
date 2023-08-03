package daos.Booking;

import models.Booking;

import java.util.Collection;
import java.util.Set;

public interface BookingDao {
    Set<Booking> getAll();
    void create(Booking book);
    void cancel(Booking book);
}
