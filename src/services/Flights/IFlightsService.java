package services.Flights;

import models.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface IFlightsService {
    Flight getFlightInformation(String id);
    Set<Flight> getAllFlightsIn24h(String city);
    List<Flight> getRelevantFlights(String startLocation,
                                    String endLocation,
                                    LocalDateTime departureDate,
                                    LocalDateTime arrivalDate,
                                    int ticketCount);
    void generateRandomFlights(int quantity);
}
