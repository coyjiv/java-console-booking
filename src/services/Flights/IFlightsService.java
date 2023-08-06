package services.Flights;

import java.time.LocalDateTime;

public interface IFlightsService {
    void displayFlightInformation(String id);
    void displayAllFlightsIn24h(String city);
    void findAndDisplayRelevantFlights(String startLocation,
                                       String endLocation,
                                       LocalDateTime departureDate,
                                       LocalDateTime arrivalDate,
                                       int ticketCount);
}
