package controllers;

import models.Booking;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingsController {
    BookingService bookingService;
    public BookingsController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public Set<Booking> getAll() {
        return this.bookingService.getAll();
    }
    public Booking getBookingById(int ID) {
        return this.bookingService.getBookingById(ID);
    }
    public Set<Booking> getAllPassengerBooks(String passenger) {
        return this.bookingService.getAllPassengerBooks(passenger);
    }
    public void create(Booking book) {
        bookingService.create(book);
    }
    public void cancel(int ID) {
        bookingService.cancel(ID);
    }
}
