package services.Flights;
import daos.Flights.FlightsDao;
import models.Flight;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightsService implements IFlightsService{
    private final FlightsDao dao;
    public FlightsService(FlightsDao dao){
        this.dao = dao;
    }
    @Override
    public Flight getFlightInformation(String id) {
       return dao.getFlightById(id);
    }

    @Override
    public Set<Flight> getAllFlightsIn24h(String city) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime timeRange24h = currentTime.plusHours(24);

        Set<Flight> flightsWithin24h = dao.getAll().stream()
                .filter(f -> f.getDepartureCity().equals(city))
                .filter(f -> {
                    LocalDateTime flightDepartureDate = f.getDepartureDate();
                    return !flightDepartureDate.isBefore(currentTime) && !flightDepartureDate.isAfter(timeRange24h);
                }).collect(Collectors.toSet());

        return flightsWithin24h;
    }

    @Override
    public List<Flight> getRelevantFlights(String startLocation, String endLocation, LocalDateTime departureDate, LocalDateTime arrivalDate, int ticketCount) {
        return dao.getAll().stream()
                .filter(f -> f.getDepartureCity().trim().equalsIgnoreCase(startLocation.trim())
                        && f.getRoute().stream().map(String::trim).anyMatch(city -> city.equalsIgnoreCase(endLocation.trim())))
                .filter(f -> {
                    // Filter by departureDate
                    return (departureDate == null || !f.getDepartureDate().isBefore(departureDate))
                            && (arrivalDate == null || !f.getArrivalDate().isAfter(arrivalDate.plusDays(1).minusSeconds(1)));
                })
                .filter(f -> f.getTicketCount() >= ticketCount)
                .collect(Collectors.toList());
    }

    @Override
    public void generateRandomFlights(int quantity) {
        dao.generateRandomFlights(quantity);
    }
}
