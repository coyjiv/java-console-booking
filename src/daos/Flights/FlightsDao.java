package daos.Flights;
import models.Flight;
import utils.FlightGenerator;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FlightsDao implements IFlightsDao{
    private final Set<Flight> flights = new HashSet<>();
    private static final String FLIGHTS_FILE_NAME = "flights.ser";

    public FlightsDao() {
        loadFlights();
    }
    @Override
    public Set<Flight> getAll() {
        return flights;
    }

    @Override
    public Flight getFlightById(String id) {
        Optional<Flight> flightOptional = flights.stream().filter(f -> f.getId().equals(id)).findFirst();
        return flightOptional.orElse(null);
    }

    @Override
    public void create(Flight flight) {
        flights.add(flight);
        saveFlights();
    }

    @Override
    public boolean edit(Flight flightToEdit, Flight editedFlight) {
        if(flights.remove(flightToEdit)){
            boolean result = flights.add(editedFlight);
            saveFlights();
            return result;
        }
        return false;
    }

    @Override
    public boolean edit(String id, Flight editedFlight) {
        if(flights.remove(getFlightById(id))){
            boolean result = flights.add(editedFlight);
            saveFlights();
            return result;
        }
        return false;
    }


    @Override
    public boolean delete(Flight flight) {
        boolean result = flights.remove(flight);
        if(result) {
            saveFlights();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = flights.remove(getFlightById(id));
        if(result) {
            saveFlights();
        }
        return result;
    }

    @Override
    public void generateRandomFlights(int quantity) {
        ArrayList<Flight> generatedFlights = (ArrayList<Flight>) FlightGenerator.generateRandomFlights(quantity);
        if(!getAll().isEmpty()){
            getAll().removeAll(getAll());
        }
        for (Flight flight : generatedFlights) {
            create(flight);
        }
    }

    private void saveFlights() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FLIGHTS_FILE_NAME))) {
            oos.writeObject(flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFlights() {
        File file = new File(FLIGHTS_FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object readObject = ois.readObject();
                if (readObject instanceof Set) {
                    flights.addAll((Set<Flight>) readObject);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
