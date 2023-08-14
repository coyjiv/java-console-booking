package controllers;

import models.Booking;
import models.Flight;
import services.Booking.BookingService;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingController {
    BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void displayAllPassengerBooks(String passenger){
        AtomicInteger atomicInteger = new AtomicInteger(1);

        bookingService.getAllPassengerBooks(passenger).stream()
                .map(booking -> atomicInteger.getAndIncrement() + " )" + booking)
                .forEach(System.out::println);
    }
    public void bookRelevantFlight(Flight flight, Scanner scanner){
        int ID = bookingService.getAll().size() + 1;
        scanner.nextLine();
        System.out.print("Введіть ваше прізвище та ім'я: ");
        String passenger = scanner.nextLine();
        Random random = new Random();
        String seat = String.valueOf(random.nextInt(flight.getTotalSeats() + 1) + flight.getTicketCount());
        Booking booking = new Booking(ID, flight, passenger, seat);
        bookingService.create(booking);
        System.out.println("Ви успішно забронювали рейс. Інформація про бронювання: " + booking);
    }
}
