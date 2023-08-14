package controllers;

import utils.ConsoleColors;
import utils.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainController implements ConsoleColors {
    private final FlightsController flightsController;
    private final BookingController bookingController;
    private final SessionController sessionController;
    private Scanner scanner = new Scanner(System.in);

    public MainController(FlightsController flightController, BookingController bookingController, SessionController sessionController) {
        this.flightsController = flightController;
        this.bookingController = bookingController;
        this.sessionController = sessionController;
    }

    public void run() {

        int authenticationChoice;

        int choice;

        while (true) {

            if (sessionController.getSession().getUser() == null) {

                Logger.systemMessage(YELLOW_BOLD + "Ви не авторизовані!" + RESET);

                Logger.displayMenuLog();

                displayAuthenticationMenu();

                if (!scanner.hasNextInt()) {
                    Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);
                    scanner.nextLine();
                    continue;
                }

                authenticationChoice = scanner.nextInt();

                Logger.correctInput(Integer.toString(authenticationChoice));

                switch (authenticationChoice) {
                    case 1:
                        if (!sessionController.registration()) continue;
                        break;
                    case 2:
                        if (!sessionController.login()) continue;
                        break;
                    default:
                        Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Невідома команда, будь ласка, спробуйте ще раз. " + RESET);
                        continue;
                }
            }

            displayMenu();

            if (!scanner.hasNextInt()) {
                Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);

                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();

            Logger.correctInput(Integer.toString(choice));

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
                case 6:
                    sessionController.logout();
                    break;
                case 0:
                    Logger.systemMessage(YELLOW_BOLD + "До побачення!" + RESET);
                    return;
                default:
                    Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Невідома команда, будь ласка, спробуйте ще раз. " + RESET);
            }
        }
    }


    private void displayMenu() {
        Logger.displayMenuLog();
        System.out.println(BLUE + """
                                            Головне меню
                               ______________________________________
                                 Оберіть дію:
                                    1. Онайн-табло
                                    2. Подивитися інформацію про рейс
                                    3. Пошук та бронювання рейсу
                                    4. Скасувати бронювання
                                    5. Мої рейси
                                    6. Завершити сесію
                                    0. Вихід
                                ______________________________________
                """ + RESET);
    }

    private void displayAuthenticationMenu() {
        System.out.println(BLUE + """
                     Оберіть дію:
                              1. Реэстрація
                              2. Вхід
                """ + RESET);
    }

    private void showFlightBoard() {
        flightsController.displayAllFlightIn24h(scanner);
    }

    private void showFlightDetails(Scanner scanner) {
        flightsController.displayFlightInformation(scanner);
    }

    private void searchAndBookFlight(Scanner scanner) {

        Logger.systemMessage(CYAN_BOLD + "Введіть місце призначення: " + RESET);
        String destination = scanner.nextLine();
        Logger.correctInput(destination);

        Logger.systemMessage(CYAN_BOLD + "Введіть дату (у форматі рік-місяць-день, наприклад, 2023-08-04): " + RESET);
        String dateInput = scanner.nextLine();
        Logger.correctInput(dateInput);

        LocalDate date = null;

        try {
            date = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {

            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Неправильний формат дати. " + RESET);
            return;
        }

        Logger.systemMessage(CYAN_BOLD + "Введіть кількість осіб: " + RESET);
        if (!scanner.hasNextInt()) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);

            scanner.nextLine();
            return;
        }
        int numPassengers = scanner.nextInt();

        Logger.correctInput(Integer.toString(numPassengers));

        scanner.nextLine();

        //TODO: Виклик методу контролера (пошук бронювання рейсу)
    }

    private void cancelBooking(Scanner scanner) {

        Logger.systemMessage(CYAN_BOLD + "Введіть айді бронювання: " + RESET);
        if (!scanner.hasNextInt()) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);

            scanner.nextLine();
            return;
        }
        int bookingId = scanner.nextInt();

        Logger.correctInput(Integer.toString(bookingId));

        //TODO: Виклик методу контролера бронювання
    }

    private void showMyBookings(Scanner scanner) {

        //TODO: Проверить и доработать!
        System.out.println(sessionController.getSession().getUser().getBookings());
    }
}