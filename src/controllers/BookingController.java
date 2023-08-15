package controllers;

import models.Booking;
import models.Flight;
import models.User;
import services.Booking.BookingService;
import utils.Logger;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.ConsoleColors.*;

public class BookingController {
    BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void displayAllPassengerBooks(String passenger){
        AtomicInteger atomicInteger = new AtomicInteger(1);

        bookingService.getAllPassengerBooks(passenger).stream()
                .map(booking -> atomicInteger.getAndIncrement() + " )" + booking)
                .forEach(b->Logger.systemMessage(GREEN_BRIGHT+ b + RESET));
    }
    public void bookRelevantFlight(Flight flight, User passengerProfile){
        int ID = bookingService.getAll().size() + 1;
        String seat = String.valueOf(flight.getTicketCount() - 1);
        Booking booking = new Booking(ID, flight, passengerProfile, seat);
        bookingService.create(booking);
        Logger.systemMessage(GREEN_BOLD + "Ви успішно забронювали рейс. Інформація про бронювання: " + booking + RESET);
    }
}
