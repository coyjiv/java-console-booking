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

    public void bookRelevantFlight(Flight flight){
        int ID = bookingService.getAll().size() + 1;
        String seat = String.valueOf(flight.getTicketCount() - 1);
        Booking booking = new Booking(ID, flight, seat);
        bookingService.create(booking);
        Logger.systemMessage(GREEN_BOLD + "Ви успішно забронювали рейс. Інформація про бронювання: " + booking + RESET);
    }

   public boolean deleteBook(int id){
       return bookingService.cancel(id);
   }

}
