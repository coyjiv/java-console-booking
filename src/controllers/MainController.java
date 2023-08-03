package controllers;

import daos.Booking.BookingDao;
import daos.Booking.SetBookingDao;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;

import java.util.Scanner;

public class MainController {
    private final FlightsController flightsController;
    private final BookingsController bookingsController;

    public MainController(FlightsController flightController, BookingsController bookingController) {
        this.flightsController = flightController;
        this.bookingsController = bookingController;
    }

    public static void run() {
        BookingDao bookingDao = new SetBookingDao();
        BookingService bookingService = new DefaultBookingService(bookingDao);
        BookingsController bookingsController = new BookingsController(bookingService);
        FlightsController flightsController = new FlightsController();
        MainController main = new MainController(flightsController, bookingsController);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(displayMenu());
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showFlightBoard();
                    break;
                case 2:
                    showFlightDetails(scanner);
                    break;
                case 3:
                    searchAndBookFlight(scanner);
                    break;
                case 4:
                    cancelBooking(scanner);
                    break;
                case 5:
                    showMyBookings(scanner);
                    break;
                case 0:
                    System.out.println("До побачення!");
                    return;
                default:
                    System.err.println("Невідома команда, будь ласка, спробуйте ще раз.");
            }
        }
    }

    private static String displayMenu() {
        return "             Головне меню:\n" +
                " _______________________________________\n" +
                "  Оберіть дію: \n" +
                " 1. Онайн-табло\n" +
                " 2. Подивитися інформацію про рейс\n" +
                " 3. Пошук та бронювання рейсу\n" +
                " 4. Скасувати бронювання\n" +
                " 5. Мої рейси\n" +
                " 0. Вихід\n" +
                "_______________________________________\n";
    }

    private static void showFlightBoard() {
        //TODO: Виклик методу контролера рейсу
    }

    private static void showFlightDetails(Scanner scanner) {
        System.out.print("Введіть айді рейсу: ");
        String flightId = scanner.nextLine();
        //TODO: Виклик методу контролера рейсу
    }

    private static void searchAndBookFlight(Scanner scanner) {
        System.out.print("Введіть місце призначення: ");
        String destination = scanner.nextLine();
        System.out.print("Введіть дату: ");
        String date = scanner.nextLine();
        System.out.print("Введіть кількість осіб: ");
        int numPassengers = scanner.nextInt();
        scanner.nextLine();
        //TODO: Виклик методу контролера (пошук бронювання рейсу)
    }

    private static void cancelBooking(Scanner scanner) {
        System.out.print("Введіть айді бронювання: ");
        String bookingId = scanner.nextLine();
        //TODO: Виклик методу контролера бронювання
    }

    private static void showMyBookings(Scanner scanner) {
        System.out.print("Введіть прізвище та ім'я: ");
        String passengerName = scanner.nextLine();
        //TODO: Виклик методу контролера бронювання
    }
}