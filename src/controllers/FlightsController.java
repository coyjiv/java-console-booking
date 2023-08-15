package controllers;

import models.Flight;
import services.Flights.FlightsService;
import utils.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static utils.ConsoleColors.*;

public class FlightsController {
    private final FlightsService flightsService;

    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
        generateFlightsIfTheyDontExist(250);
    }

    public void displayAllFlights() {
        for (Flight flight:flightsService.getAll()) {
            Logger.systemMessage(GREEN_BOLD + flight + RESET);
        }
        System.out.println("\n\n\n");
    }

    public void displayFlightInformation(Scanner scanner) {
        System.out.print("\nВведіть id рейсу для пошуку: ");
        String flightId = scanner.nextLine();
        System.out.println("Результати пошуку : ");
        Flight foundFlight = flightsService.getFlightInformation(flightId);
        if (foundFlight != null) {
            Logger.systemMessage(GREEN_BOLD + foundFlight + RESET);
        } else {
            Logger.notCorrectInput(RED_BOLD_BRIGHT+"Нічого не знайдено."+RESET);
        }
    }

    public void displayAllFlightIn24h(String city) {
        System.out.println("\nРезультати пошуку : ");
        Set<Flight> results = flightsService.getAllFlightsIn24h(city);
        if (results.isEmpty()) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT+"Нічого не знайдено."+RESET);
        } else {
            results.forEach(f->Logger.systemMessage(GREEN_BOLD + f + RESET));
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
