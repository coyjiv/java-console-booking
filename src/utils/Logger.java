package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Logger {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Logger.class.getName());

    static {
        try {
            // Отримуємо логгер і створюємо обробник для файлу
            LOGGER.setUseParentHandlers(false); // Вимикаємо наслідування обробників вищестоячих логерів

            // Обробник для файлу
            FileHandler fileHandler = new FileHandler("flight_booking.log", false);
            fileHandler.setFormatter(new CustomFormatter());
            LOGGER.addHandler(fileHandler);

            // Обробник для консолі
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter() {
                @Override
                public String format(LogRecord record) {
                    String message = record.getMessage();
                    // Перевіряємо, чи повідомлення має текст "Вивід меню" або "Користувач ввів :",
                    // якщо так, то повертаємо порожній рядок, тим самим не виводимо його в консолі
                    if (message.contains("Вивід меню") || message.contains("Користувач ввів :")) {
                        return "";
                    }
                    return super.formatMessage(record) + "\n";
                }
            });
            consoleHandler.setLevel(Level.ALL);
            LOGGER.addHandler(consoleHandler);

            // Встановлюємо рівень журналювання на ALL для обробника файлу
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static class CustomFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
            sb.append(dateFormat.format(new Date(record.getMillis())));
            sb.append(" ");
            sb.append(record.getLevel().toString());
            sb.append(": ");
            sb.append(removeColorFormatting(record.getMessage())); // Метод для видалення кольорового форматування
            sb.append("\n");

            return sb.toString();
        }

        private String removeColorFormatting(String message) {
            // Вираз для видалення escape послідовностей
            return message.replaceAll("\\u001B\\[[;\\d]*m", "");
        }
    }

    public static void systemMessage(String message) {
        LOGGER.info(message);
    }

    public static void displayMenuLog() {
        LOGGER.info(" Вивід меню ");
    }

    public static void notCorrectInput(String message) {
        LOGGER.warning("Помилка: " + message);
    }

    public static void correctInput(String message) {
        LOGGER.info(" Користувач ввів : " + message);
    }
}