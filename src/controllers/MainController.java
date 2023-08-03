package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainController {
    private final FlightsController flightsController;
    private final BookingsController bookingsController;

    public MainController(FlightsController flightController, BookingsController bookingController) {
        this.flightsController = flightController;
        this.bookingsController = bookingController;
    }

    public void run() {

        int choice;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(displayMenu());
            if (!isIntEntered(scanner)) {
                System.err.println("Ви ввели не число, будь ласка, введіть число.");
                continue;
            }
            choice = scanner.nextInt();
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

    private boolean isIntEntered(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return false;
        }
        return true;
    }

    private String displayMenu() {
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

    private void showFlightBoard() {
        //TODO: Виклик методу контролера рейсу
    }

    private void showFlightDetails(Scanner scanner) {
        System.out.print("Введіть айді рейсу: ");

        if (!isIntEntered(scanner)) {
            System.err.println("Ви ввели не число, будь ласка, наступного разу введіть число.");
            return;
        }
        int flightId = scanner.nextInt();
        //TODO: Виклик методу контролера рейсу
    }

    private void searchAndBookFlight(Scanner scanner) {
        System.out.print("Введіть місце призначення: ");
        String destination = scanner.nextLine();

        System.out.print("Введіть дату (у форматі рік-місяць-день, наприклад, 2023-08-04): ");
        String dateInput = scanner.nextLine();
        LocalDate date = null;

        try {
            date = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.err.println("Помилка: Неправильний формат дати.");
            return;
        }

        System.out.print("Введіть кількість осіб: ");
        if (!isIntEntered(scanner)){
            System.err.println("Ви ввели не число, будь ласка, наступного разу введіть число.");
        }
        int numPassengers = scanner.nextInt();
        scanner.nextLine();

        //TODO: Виклик методу контролера (пошук бронювання рейсу)
    }

    private void cancelBooking(Scanner scanner) {
        System.out.print("Введіть айді бронювання: ");
          if (!isIntEntered(scanner)) {
              System.err.println("Ви ввели не число, будь ласка, наступного разу введіть число.");
              return;
        }
        int bookingId = scanner.nextInt();
        //TODO: Виклик методу контролера бронювання
    }

    private void showMyBookings(Scanner scanner) {
        System.out.print("Введіть прізвище та ім'я: ");
        String passengerName = scanner.nextLine();
        //TODO: Виклик методу контролера бронювання
    }
}