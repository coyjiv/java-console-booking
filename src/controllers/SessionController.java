package controllers;

import models.Login;
import models.Password;
import models.Session;
import services.Session.SessionService;
import utils.ConsoleColors;
import utils.Logger;

import java.util.Scanner;

public class SessionController implements ConsoleColors {
    private final SessionService sessionsService;

    private final Scanner scanner = new Scanner(System.in);

    public SessionController(SessionService sessionsService) {
        this.sessionsService = sessionsService;
    }

    public Session getSession() {
        return sessionsService.getSession();
    }

    public boolean login() {
        Logger.systemMessage(CYAN + "Будь ласка , введіть логін :" + RESET);

        Login login = new Login(scanner.nextLine().trim());

        Logger.correctInput(login.toString());

        Logger.systemMessage(CYAN + "Будь ласка , введіть пароль :" + RESET);
        Password password = new Password(scanner.nextLine().trim());

        Logger.correctInput(password.toString());

        if (login.toString().length() < 1 || password.toString().length() < 1) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Логін та пароль не повинні бути пустими!" + RESET);
            return false;
        }

        boolean resultOfLogin = sessionsService.login(login, password);

        if (resultOfLogin) {
            Logger.systemMessage(YELLOW_BOLD + "Користувача знайдено! Ви авторизовані!" + RESET);
        } else {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Користувача не знайдено! спробуйте ще раз. " + RESET);
        }

        return resultOfLogin;
    }

    public boolean registration() {
        Logger.systemMessage(CYAN_BOLD + "Будь ласка , введіть ваше ім'я: " + RESET);
        String name = scanner.nextLine().trim();
        Logger.correctInput(name);

        Logger.systemMessage(CYAN_BOLD + "Будь ласка , введіть ваше прізвище: " + RESET);
        String surname = scanner.nextLine().trim();
        Logger.correctInput(surname);

        Logger.systemMessage(CYAN + "Будь ласка , введіть логін :" + RESET);

        Login login = new Login(scanner.nextLine().trim());

        Logger.correctInput(login.toString());

        Logger.systemMessage(CYAN + "Будь ласка , введіть пароль :" + RESET);
        Password password = new Password(scanner.nextLine().trim());

        Logger.correctInput(password.toString());


        if (name.isEmpty() || surname.isEmpty() || login.toString().isEmpty() || password.toString().isEmpty()) {
            Logger.notCorrectInput(RED_BOLD_BRIGHT + " Помилка: Прізвище, ім'я, логін та пароль не повинні бути пустими!" + RESET);
            return false;
        }

        boolean resultOfRegistration = sessionsService.registration(name,surname,login,password);

        if (resultOfRegistration){
            Logger.systemMessage(YELLOW_BOLD + "Користувача створено! Ви авторизовані!" + RESET);
        }else {
            Logger.systemMessage(RED_BOLD_BRIGHT + "Користувача знайдено! Спробуйте дію : 'Вхід' !" + RESET);
        }

        return resultOfRegistration;

    }

    public void logout() {
        sessionsService.logout();
    }
}