package daos.Booking.test;

import daos.Booking.BookingDao;
import daos.Booking.FileBookingDao;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TestFileBookingDao {
    private BookingDao bookingDao;
    @BeforeEach
    public void beforeEach(){
        bookingDao = new FileBookingDao();
        File file = new File("bookings.ser");
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void testCreateBooking(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.of(2023, Month.AUGUST, 11, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 12, 4,25),
                new ArrayList<>(List.of("Buharest")),
                55
                );
        Booking booking = new Booking(1, flight, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "47");

        assertEquals(0, bookingDao.getAll().size());
        bookingDao.create(booking);
        assertEquals(1, bookingDao.getAll().size());
    }

    @Test
    public void testCancelBookingByID(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.of(2023, Month.AUGUST, 11, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 12, 4,25),
                new ArrayList<>(List.of("Buharest")),
                55
        );
        Booking booking = new Booking(1, flight, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "47");

        bookingDao.create(booking);
        assertEquals(1, bookingDao.getAll().size());
        bookingDao.cancel(1);
        assertEquals(0, bookingDao.getAll().size());
    }
    @Test
    public void testCancelBookingByObj(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.of(2023, Month.AUGUST, 11, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 12, 4,25),
                new ArrayList<>(List.of("Buharest")),
                55
        );
        Booking booking = new Booking(1, flight, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "47");

        bookingDao.create(booking);
        assertEquals(1, bookingDao.getAll().size());
        bookingDao.cancel(booking);
        assertEquals(0, bookingDao.getAll().size());
    }

    @Test
    public void testGetBookingByExcitingId(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.of(2023, Month.AUGUST, 11, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 12, 4,25),
                new ArrayList<>(List.of("Buharest")),
                55
        );
        Booking booking = new Booking(1, flight, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "47");

        bookingDao.create(booking);

        Booking foundedBooking = bookingDao.getBookingById(1);
        assertEquals(booking, foundedBooking);
    }

    @Test
    public void testGetBookingByNotExcitingId(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.of(2023, Month.AUGUST, 11, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 12, 4,25),
                new ArrayList<>(List.of("Buharest")),
                55
        );
        Booking booking = new Booking(1, flight, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "47");

        bookingDao.create(booking);

        assertThrows(NoSuchElementException.class, () -> bookingDao.getBookingById(4));
    }
}
