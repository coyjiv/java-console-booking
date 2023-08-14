package controllers;

import models.Booking;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;

import java.util.Set;

public class BookingsController {
    BookingService bookingService;

    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Set<Booking> getAll() {
        return bookingService.getAll();
    }

}
