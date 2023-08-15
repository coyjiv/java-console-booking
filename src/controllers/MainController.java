package controllers;

import models.Booking;
import models.Flight;
import utils.ConsoleColors;
import utils.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainController implements ConsoleColors {
    private final FlightsController flightsController;
    private final BookingController bookingController;
    private final SessionController sessionController;
    private final Scanner scanner = new Scanner(System.in);

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
                    case 1 -> {
                        if (!sessionController.registration()) continue;
                    }
                    case 2 -> {
                        if (!sessionController.login()) continue;
                    }
                    case 0 -> System.exit(0);
                    default -> {
                        Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Невідома команда, будь ласка, спробуйте ще раз. " + RESET);
                        continue;
                    }
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
                case 1 -> showFlightBoard();
                case 2 -> showFlightDetails(scanner);
                case 3 -> searchAndBookFlight(scanner);
                case 4 -> cancelBooking(scanner);
                case 5 -> showMyBookings();
                case 6 -> sessionController.logout();
                case 0 -> {
                    Logger.systemMessage(YELLOW_BOLD + "До побачення!" + RESET);
                    return;
                }
                default ->
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
                                    1. Онлайн-табло
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
                              1. Реєстрація
                              2. Логін
                              0. Вийти з додатку
                """ + RESET);
    }

    private void showFlightBoard() {
        Logger.displayMenuLog();
        System.out.println("Введіть місто для пошуку рейсів (англійською, наприклад Kyiv): , або просто натисніть Enter щоб побачити всі рейси");
        System.out.print("Місто : ");
        String startLocation = scanner.nextLine();
        if (!startLocation.isEmpty()) {
            flightsController.displayAllFlightIn24h(startLocation);
        } else {
            System.out.println("Місто не вказано, показую усі рейси ");
            flightsController.displayAllFlights();
        }
    }

    private void showFlightDetails(Scanner scanner) {
        flightsController.displayFlightInformation(scanner);
    }

    private void searchAndBookFlight(Scanner scanner) {
        System.out.print("Введіть стартове місто для пошуку рейсів (англійською, наприклад Kyiv): ");
        String startLocation = scanner.nextLine();
        System.out.print("Введіть місто призначення для пошуку рейсів (англійською, наприклад Kyiv): ");
        String endLocation = scanner.nextLine();
        System.out.print("*Необов'язково - Введіть бажану початкову дату (або дату і час) рейсу (формат 31.07.2023): ");

        LocalDateTime departureDate = null;

        try {
            departureDate = flightsController.parseDate(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Неправильний формат дати, продовжую без початкової дати.");
        }

        System.out.print("*Необов'язково - Введіть бажану кінцеву дату (або дату і час) рейсу (формат 31.07.2023): ");

        LocalDateTime arrivalDate = null;
        try {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                arrivalDate = flightsController.parseDate(input);
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
        List<Flight> resultOfSearch = flightsController.displayAllRelevantFlights(startLocation, endLocation, departureDate, arrivalDate, ticketCount);

        if (resultOfSearch.isEmpty()) {
            System.out.println("Нічого не знайдено");
        } else {
            resultOfSearch.forEach(System.out::println);
        }

        int choice = 0;
        while (true) {
            System.out.println("Якщо бажаєте забронювати рейс, введіть його порядковий номер. Якщо бажаєте вийти, натисніть 0.");
            System.out.print("\nID рейсу для бронювання : ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1) {
                    bookingController.bookRelevantFlight(flightsController.getFlight(String.valueOf(choice)));
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

    private void cancelBooking(Scanner scanner) {

        Logger.systemMessage(CYAN_BOLD + "Введіть айді бронювання: " + RESET);
        if (!scanner.hasNextInt()) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Ви ввели не число, будь ласка, введіть число. " + RESET);

            scanner.nextLine();
            return;
        }
        int bookingId = scanner.nextInt();

        Logger.correctInput(Integer.toString(bookingId));

       if( bookingController.deleteBook(bookingId)){
           Logger.correctInput(GREEN_BOLD + "Бронювання видалено!" + RESET);
       }else {
           Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Бронювання НЕ видалено!" + RESET);
       }
    }

    private void showMyBookings() {
        if (sessionController.getSession().getUser().getBookings().size() < 1){
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Ви ще нічого не забронювали!" + RESET);
            return;
        }
        for (Booking booking : sessionController.getSession().getUser().getBookings()) {
            Logger.systemMessage(GREEN_BOLD + booking.toString() + RESET);
        }
    }
}