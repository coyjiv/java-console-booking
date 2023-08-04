package controllers;

import utils.ConsoleColors;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainController implements ConsoleColors {
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
            displayMenu();

            if (!scanner.hasNextInt()) {
                System.err.println(RED_BOLD_BRIGHT + "Ви ввели не число, будь ласка, введіть число." + RESET);
                scanner.nextLine();
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
                    System.out.println(YELLOW_BOLD + "До побачення!" + RESET);
                    return;
                default:
                    System.err.println(RED_BOLD_BRIGHT + "Невідома команда, будь ласка, спробуйте ще раз." + RESET);
            }
        }
    }

    private void displayMenu() {
        System.out.println(BLUE + """
                                            Головне меню
                               ______________________________________
                                 Оберіть дію:
                                    1. Онайн-табло
                                    2. Подивитися інформацію про рейс
                                    3. Пошук та бронювання рейсу
                                    4. Скасувати бронювання
                                    5. Мої рейси
                                    0. Вихід
                                ______________________________________
                """ + RESET);
    }

    private void showFlightBoard() {
        //TODO: Виклик методу контролера рейсу
    }

    private void showFlightDetails(Scanner scanner) {
        System.out.print(CYAN_BOLD + "Введіть айді рейсу: " + RESET);

        if (!scanner.hasNextInt()) {
            System.err.println(RED_BOLD_BRIGHT + "Ви ввели не число, будь ласка, введіть число." + RESET);
            scanner.nextLine();
            return;
        }
        int flightId = scanner.nextInt();
        //TODO: Виклик методу контролера рейсу
    }

    private void searchAndBookFlight(Scanner scanner) {
        System.out.print(CYAN_BOLD + "Введіть місце призначення: " + RESET);
        String destination = scanner.nextLine();

        System.out.print(CYAN_BOLD + "Введіть дату (у форматі рік-місяць-день, наприклад, 2023-08-04): " + RESET);
        String dateInput = scanner.nextLine();
        LocalDate date = null;

        try {
            date = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.err.println(RED_BOLD_BRIGHT + "Помилка: Неправильний формат дати." + RESET);
            return;
        }

        System.out.print(CYAN_BOLD + "Введіть кількість осіб: " + RESET);
        if (!scanner.hasNextInt()) {
            System.err.println(RED_BOLD_BRIGHT + "Ви ввели не число, будь ласка, введіть число." + RESET);
            scanner.nextLine();
            return;
        }
        int numPassengers = scanner.nextInt();
        scanner.nextLine();

        //TODO: Виклик методу контролера (пошук бронювання рейсу)
    }

    private void cancelBooking(Scanner scanner) {
        System.out.print(CYAN_BOLD + "Введіть айді бронювання: " + RESET);
        if (!scanner.hasNextInt()) {
            System.err.println(RED_BOLD_BRIGHT + "Ви ввели не число, будь ласка, введіть число." + RESET);
            scanner.nextLine();
            return;
        }
        int bookingId = scanner.nextInt();
        //TODO: Виклик методу контролера бронювання
    }

    private void showMyBookings(Scanner scanner) {
        System.out.print(CYAN_BOLD + "Введіть прізвище та ім'я: " + RESET);
        String passengerName = scanner.nextLine();
        //TODO: Виклик методу контролера бронювання
    }
}