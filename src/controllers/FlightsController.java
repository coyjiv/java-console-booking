package controllers;

import models.Flight;
import services.Flights.FlightsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class FlightsController {
    private final FlightsService flightsService;
    private final BookingController bookingController;

    public FlightsController(FlightsService flightsService, BookingController bookingController) {
        this.flightsService = flightsService;
        this.bookingController = bookingController;
        generateFlightsIfTheyDontExist(250);
    }

    public void displayAllFlights() {
        for (Flight flight:flightsService.getAll()) {
            System.out.println(flight.toString());
        }
        System.out.println("\n\n\n");
    }

    public void displayFlightInformation(Scanner scanner) {
        System.out.print("\nВведіть id рейсу для пошуку: ");
        String flightId = scanner.nextLine();
        System.out.println("Результати пошуку : ");
        Flight foundFlight = flightsService.getFlightInformation(flightId);
        if (foundFlight != null) {
            System.out.println(foundFlight);
        } else {
            System.out.println("Нічого не знайдено.");
        }
    }

    public void displayAllFlightIn24h(String city) {
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

    public Flight getFlight(String id){
        return flightsService.getFlight(id);
    }

    public void generateFlightsIfTheyDontExist(int quantity){
        flightsService.generateRandomFlights(quantity);
    }
}
