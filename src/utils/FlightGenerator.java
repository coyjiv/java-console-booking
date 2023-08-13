package utils;

import models.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FlightGenerator {

    private static final List<String> CITIES = Arrays.asList("New York", "London", "Paris", "Tokyo", "Hongkong", "Kyiv", "Berlin", "Sydney", "Toronto", "Mumbai");
    private static final Random RAND = new Random();

    public static List<Flight> generateRandomFlights(int numberOfFlights) {
        List<Flight> flights = new ArrayList<>();

        for (int i = 0; i < numberOfFlights; i++) {
            String id = String.valueOf(i);
            LocalDateTime date = getRandomDate();
            LocalDateTime departureDateTime = getRandomDepartureDateTime();
            ArrayList<String> route = getRandomRoute();

            int seats = RAND.nextInt(200) + 50;

            flights.add(new Flight(id, date, departureDateTime, route, seats));
        }


        return flights;
    }

    private static LocalDateTime getRandomDate() {
        int daysToSubtract = RAND.nextInt(10);
        int hoursToSubtract = RAND.nextInt(24);
        return LocalDateTime.now().minusDays(daysToSubtract).minusHours(hoursToSubtract);
    }

    private static LocalDateTime getRandomDepartureDateTime() {
        int daysToAdd = RAND.nextInt(10);
        int hoursToAdd = RAND.nextInt(24);
        int minutesToAdd = RAND.nextInt(60);
        int secondsToAdd = RAND.nextInt(60);
        return LocalDateTime.now().plusDays(daysToAdd).plusHours(hoursToAdd).plusMinutes(minutesToAdd).plusSeconds(secondsToAdd);
    }


    private static ArrayList<String> getRandomRoute() {
        ArrayList<String> route = new ArrayList<>();
        String startCity = CITIES.get(RAND.nextInt(CITIES.size()));
        String endCity;
        do {
            endCity = CITIES.get(RAND.nextInt(CITIES.size()));
        } while (startCity.equals(endCity));

        route.add(startCity);
        if(RAND.nextInt(10)>3){
            String finalEndCity = endCity;
            route.add(CITIES.stream().filter(c->!c.equals(startCity)&&!c.equals(finalEndCity)).toList().get(0));
        }
        route.add(endCity);

        return route;
    }

    public static void main(String[] args) {
        List<Flight> flights = generateRandomFlights(10);
        flights.forEach(System.out::println);
    }
}
