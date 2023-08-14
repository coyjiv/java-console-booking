package controllers;

import daos.Booking.BookingDao;
import daos.Booking.FileBookingDao;
import daos.Flights.FlightsDao;
import models.Flight;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;
import services.Flights.FlightsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FlightsController {
    private final FlightsService flightsService;
    private final BookingController bookingController;

    public FlightsController(FlightsService flightsService, BookingController bookingController) {
        this.flightsService = flightsService;
        this.bookingController = bookingController;
    }

    public void displayFlightInformation(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введіть id рейсу для пошуку: ");
        String flightId = scanner.nextLine();
        System.out.print("Результати пошуку : ");
        Flight foundFlight = flightsService.getFlightInformation(flightId);
        if (foundFlight != null) {
            System.out.println(foundFlight);
        } else {
            System.out.println("Нічого не знайдено.");
        }
    }

    public void displayAllFlightIn24h(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Введіть місто для пошуку рейсів (англійською, наприклад Kyiv): ");
        String city = scanner.nextLine();
        System.out.println("\nРезультати пошуку : ");
        Set<Flight> results = flightsService.getAllFlightsIn24h(city);
        if (results.isEmpty()) {
            System.out.println("Нічого не знайдено");
        } else {
            results.forEach(System.out::println);
        }
    }

    public List<Flight>  displayAllRelevantFlights(String startLocation, String endLocation,LocalDateTime departureDate,LocalDateTime arrivalDate,int ticketCount) {

        List<Flight> results = flightsService.getRelevantFlights(startLocation, endLocation, departureDate, arrivalDate, ticketCount);
        if (results.isEmpty()) {
            System.out.println("Нічого не знайдено");
        }
        return  results;

    }

    public LocalDateTime parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(dateStr, formatter).atStartOfDay();
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
