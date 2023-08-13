package controllers;

import daos.Flights.FlightsDao;
import models.Flight;
import services.Flights.FlightsService;
import utils.FlightGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class FlightsController {
    private final FlightsService flightsService;

    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

// TODO:    !!!! for development purposes, remove in prod !!!

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        FlightsDao flightsDao = new FlightsDao();
//        FlightsService flightsService1 = new FlightsService(flightsDao);
//        FlightsController flightsController = new FlightsController(flightsService1);
//        while (true) {
//            System.out.println("""
//                    1. displayFlightInformation
//                    2. displayAllFlightsIn24h
//                    3. displayAllRelevantFlights
//                    4. fillMockFlights
//                    6. showAllFlights
//                    """);
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1 -> flightsController.displayFlightInformation(scanner);
//                case 2 -> flightsController.displayAllFlightIn24h(scanner);
//                case 3 -> flightsController.displayAllRelevantFlights(scanner);
//                case 4 -> {
//                    flightsService1.generateRandomFlights(100);
//                }
//                case 6 -> flightsDao.getAll().forEach(System.out::println);
//                case 5 -> System.exit(0);
//            }
//        }
//    }

    private void displayFlightInformation(Scanner scanner){
        scanner.nextLine();
        System.out.println("Введіть id рейсу для пошуку: ");
        String flightId = scanner.nextLine();
        System.out.print("Результати пошуку : ");
        Flight foundFlight = flightsService.getFlightInformation(flightId);
        if(foundFlight != null){
            System.out.println(foundFlight);
        } else {
            System.out.println("Нічого не знайдено.");
        }
    }

    private void displayAllFlightIn24h(Scanner scanner){
        scanner.nextLine();
        System.out.print("Введіть місто для пошуку рейсів (англійською, наприклад Kyiv): ");
        String city = scanner.nextLine();
        System.out.println("\nРезультати пошуку : ");
        Set<Flight> results = flightsService.getAllFlightsIn24h(city);
        if (results.isEmpty()){
            System.out.println("Нічого не знайдено");
        } else {
            results.forEach(System.out::println);
        }
    }

    private void displayAllRelevantFlights(Scanner scanner){
        scanner.nextLine();

            System.out.print("Введіть стартове місто для пошуку рейсів (англійською, наприклад Kyiv): ");
            String startLocation = scanner.nextLine();
            System.out.print("Введіть місто призначення для пошуку рейсів (англійською, наприклад Kyiv): ");
            String endLocation = scanner.nextLine();
            System.out.print("*Необов'язково - Введіть бажану дату (або дату і час) рейсу (формат 31.07.2023): ");

            LocalDateTime departureDate = null;

            try {
                departureDate = parseDate(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Неправильний формат дати, продовжую без початкової дати.");
            }

        System.out.print("*Необов'язково - Введіть бажану дату (або дату і час) рейсу (формат 31.07.2023): ");

            LocalDateTime arrivalDate = null;
            try {
                String input = scanner.nextLine();
            if (!input.isEmpty()) {
                arrivalDate = parseDate(input);
            }
            } catch (DateTimeParseException e) {
                System.out.println("Неправильний формат дати, продовжую без кінцевої дати.");
            }


        int ticketCount = 0;

            while (true) {
            System.out.print("Кількість квитків: ");
                try {
                    ticketCount = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Будь ласка, введіть коректну кількість квитків.");
                    scanner.nextLine();
                }
            }
        System.out.println("Результати пошуку : ");

            Set<Flight> results = flightsService.getRelevantFlights(startLocation, endLocation, departureDate, arrivalDate, ticketCount);

            if(results.isEmpty()){
                System.out.println("Нічого не знайдено");
            } else {
                results.forEach(System.out::println);
            }


    }

    private LocalDateTime parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(dateStr, formatter).atStartOfDay();
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
