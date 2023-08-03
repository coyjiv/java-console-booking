package services.Flights;

import daos.Flights.FlightsDao;

public class FlightsService implements FlightServiceInterface {
    private FlightsDao flightsDao = new FlightsDao();

    public FlightsService(FlightsDao flightsDao) {
    this.flightsDao = flightsDao;
    }
}
