package services.Booking.test;

import daos.Booking.BookingDao;
import daos.Booking.FileBookingDao;
import daos.Users.UsersDao;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class testDefaultBookingService {
    BookingDao bookingDao;
    BookingService bookingService;
    @BeforeEach
    public void beforeEach(){
        bookingDao = new FileBookingDao();
        bookingService = new DefaultBookingService(bookingDao);
    }

    @Test
    public void testGetAllPassengerBooks(){
        Flight flight1 = new Flight(
                "1",
                LocalDateTime.of(2023, Month.AUGUST, 11, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 12, 4,25),
                new ArrayList<>(List.of("Buharest")),
                55
        );
        Flight flight2 = new Flight(
                "2",
                LocalDateTime.of(2023, Month.AUGUST, 13, 14,55),
                LocalDateTime.of(2023, Month.AUGUST, 15, 4,25),
                new ArrayList<>(List.of("Dubai")),
                55
        );
        Booking booking1 = new Booking(1, flight1, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "47");
        Booking booking2 = new Booking(2, flight2, new User("Test","TestUser",new Login("testLog"),new Password("testPass")), "24");

        bookingService.create(booking1);
        bookingService.create(booking2);

        Set<Booking> passengerBookings = bookingService.getAllPassengerBooks("Kirilenko Andriy");
        assertEquals(2, passengerBookings.size());
    }
}
