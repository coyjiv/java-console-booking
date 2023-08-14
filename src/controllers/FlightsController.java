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

// TODO:    !!!! for development purposes, remove in prod !!!

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlightsDao flightsDao = new FlightsDao();
        FlightsService flightsService1 = new FlightsService(flightsDao);

        BookingDao bookingDao = new FileBookingDao();
        BookingService bookingService = new DefaultBookingService(bookingDao);

        BookingController bookingController = new BookingController(bookingService);
        FlightsController flightsController = new FlightsController(flightsService1, bookingController);

        while (true) {
            System.out.println("""
                    1. displayFlightInformation
                    2. displayAllFlightsIn24h
                    3. displayAllRelevantFlights
                    4. fillMockFlights
                    6. showAllFlights
                    7. showAllPassengerBooking
                    """);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> flightsController.displayFlightInformation(scanner);
                case 2 -> flightsController.displayAllFlightIn24h(scanner);
                case 3 -> flightsController.displayAllRelevantFlights(scanner);
                case 4 -> {
                    flightsService1.generateRandomFlights(100);
                }
                case 6 -> flightsDao.getAll().forEach(System.out::println);
                case 7 -> bookingController.displayAllPassengerBooks("Kiselevych Anton");
                case 5 -> System.exit(0);
            }
        }
    }

    private void displayFlightInformation(Scanner scanner) {
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

    private void displayAllFlightIn24h(Scanner scanner) {
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

    private void displayAllRelevantFlights(Scanner scanner) {
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

        List<Flight> results = flightsService.getRelevantFlights(startLocation, endLocation, departureDate, arrivalDate, ticketCount);
        if (results.isEmpty()) {
            System.out.println("Нічого не знайдено");
        } else {
            AtomicInteger counter = new AtomicInteger(1);
            results.stream()
                    .map(flight -> counter.getAndIncrement() + " )" + flight)
                    .forEach(System.out::println);
        }


        int choice = 0;
        while (true) {
            System.out.println("Якщо бажаєте забронювати рейс, введіть його порядковий номер. Якщо бажаєте вийти, натисніть 0.");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= results.size()) {
                    bookingController.bookRelevantFlight(results.get(choice - 1), scanner);
                } else if (choice == 0) {
                        break;
                } else {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Будь ласка, введіть коректну опцію.");
                scanner.nextLine();
            }
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
