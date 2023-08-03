package controllers;

import services.Booking.BookingService;
import services.Booking.DefaultBookingService;

public class BookingsController {
    BookingService bookingService;

    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


}
