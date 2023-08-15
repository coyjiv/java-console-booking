package daos.Flights;

import models.Flight;

import java.util.Set;

public interface IFlightsDao {

    Set<Flight> getAll();

    Flight getFlightById(String id);

    void create(Flight flight);

    boolean edit(Flight flightToEdit, Flight editedFlight);

    boolean edit(String id, Flight editedFlight);

    boolean delete(Flight flight);

    boolean delete(String id);

    void generateRandomFlightsIfTheyDontExist(int quantity);
}
