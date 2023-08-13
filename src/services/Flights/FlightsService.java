package services.Flights;
import daos.Flights.FlightsDao;
import models.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsService implements IFlightsService{
    private final FlightsDao dao;
    public FlightsService(FlightsDao dao){
        this.dao = dao;
    }
    @Override
    public void displayFlightInformation(String id) {
        System.out.println(dao.getFlightById(id));
    }

    @Override
    public void displayAllFlightsIn24h(String city) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime timeRange24h = currentTime.plusHours(24);

        List<Flight> flightsWithin24h = dao.getAll().stream()
                .filter(f -> f.getDepartureCity().equals(city))
                .filter(f -> {
                    LocalDateTime flightDepartureDate = f.getDepartureDate();
                    return !flightDepartureDate.isBefore(currentTime) && !flightDepartureDate.isAfter(timeRange24h);
                }).toList();

        flightsWithin24h.forEach(System.out::println);
    }

    //    @Override
//    public void findAndDisplayRelevantFlights(String startLocation, String endLocation, LocalDateTime departureDate, LocalDateTime arrivalDate, int ticketCount) {
//        List<Flight> results = dao.getAll().stream()
//                .filter(f -> f.getDepartureCity().equals(startLocation)
//                        && (f.getRoute().contains(endLocation) && f.getRoute().indexOf(endLocation) != 0)
//                        && f.getTicketCount() >= ticketCount)
//                .filter(f -> {
//                    LocalDateTime flightDepartureDate = f.getDepartureDate();
//                    LocalDateTime flightArrivalDate = f.getArrivalDate();
//                    return !flightDepartureDate.isBefore(departureDate) && !flightArrivalDate.isAfter(arrivalDate);
//                }).toList();
//
//        results.forEach(System.out::println);
//    }

    @Override
    public void findAndDisplayRelevantFlights(String startLocation, String endLocation, LocalDateTime departureDate, LocalDateTime arrivalDate, int ticketCount) {

    }
}
