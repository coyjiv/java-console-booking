package controllers;

import models.Login;
import models.Password;
import utils.ConsoleColors;
import utils.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainController implements ConsoleColors {
    private final FlightsController flightsController;
    private final BookingController bookingController;
    private final SessionController sessionController;
    private Logger logger;

    public MainController(FlightsController flightController, BookingController bookingController, SessionController sessionController) {
        this.flightsController = flightController;
        this.bookingController = bookingController;
        this.sessionController = sessionController;
    }

    public void run() {

        int choice;

        Scanner scanner = new Scanner(System.in);

        while (true) {

            if (sessionController.getSession().getUser() == null) {

                Logger.notCorrectInput(YELLOW_BOLD + "Ви не авторизовані!" + RESET);

                Logger.systemMessage(CYAN + "Будь ласка , введіть логін :" + RESET);

                Login login = new Login(scanner.nextLine().trim());

                logger.correctInput(login.toString());

                Logger.systemMessage(CYAN + "Будь ласка , введіть пароль :" + RESET);
                Password password = new Password(scanner.nextLine().trim());

                logger.correctInput(password.toString());

                if (login.toString().length() < 1 || password.toString().length() < 1) {
                    Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Логін та пароль не повинні бути пустими!" + RESET);
                    continue;
                }
                sessionController.authentication(login, password);
            }

            displayMenu();

            if (!scanner.hasNextInt()) {
                Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);
              
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();

            logger.correctInput(Integer.toString(choice));

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

    private void showFlightBoard() {
        //TODO: Виклик методу контролера рейсу
    }

    private void showFlightDetails(Scanner scanner) {

        Logger.systemMessage(CYAN_BOLD + "Введіть айді рейсу: " + RESET);

        if (!scanner.hasNextInt()) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);
            scanner.nextLine();
            return;
        }
        int flightId = scanner.nextInt();
        logger.correctInput(Integer.toString(flightId));
        //TODO: Виклик методу контролера рейсу
    }

    private void searchAndBookFlight(Scanner scanner) {

        Logger.systemMessage(CYAN_BOLD + "Введіть місце призначення: " + RESET);
        String destination = scanner.nextLine();
        logger.correctInput(destination);

        Logger.systemMessage(CYAN_BOLD + "Введіть дату (у форматі рік-місяць-день, наприклад, 2023-08-04): " + RESET);
        String dateInput = scanner.nextLine();
        logger.correctInput(dateInput);
      
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

        logger.correctInput(Integer.toString(numPassengers));

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

        logger.correctInput(Integer.toString(bookingId));

        //TODO: Виклик методу контролера бронювання
    }

    private void showMyBookings(Scanner scanner) {

        if (sessionController.getSession().getUser().getName() == null || sessionController.getSession().getUser().getSurname() == null) {

            Logger.systemMessage(CYAN_BOLD + "Введіть прізвище: " + RESET);
            String surname = scanner.nextLine().trim();
            logger.correctInput(surname);

            Logger.systemMessage(CYAN_BOLD + "Введіть ім'я: " + RESET);
            String name = scanner.nextLine().trim();
            logger.correctInput(name);

            if (surname.length() < 1 || name.length() < 1) {
                Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: прізвище та ім'я не повинні бути пустими!" + RESET);
            } else {
                sessionController.getSession().getUser().setSurname(surname);
                sessionController.getSession().getUser().setName(name);
            }

        }
        //TODO: Доработать!
        sessionController.getSession().getUser().getBookings();
        //TODO: Виклик методу контролера бронювання
    }
}