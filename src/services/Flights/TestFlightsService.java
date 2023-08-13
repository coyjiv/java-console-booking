package services.Flights;

import daos.Flights.FlightsDao;
import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestFlightsService {
    @Mock
    private FlightsDao flightsDao;

    @InjectMocks
    private FlightsService flightsService; // Assuming FlightsService implements IFlightsService

    @BeforeEach
    public void beforeEach(){
        File file = new File("flights.ser");
        if(file.exists()){
            file.delete();
        }
    }
    @Test
    public void testGetFlightInformation() {
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        when(flightsDao.getFlightById("1")).thenReturn(flight);

        Flight result = flightsService.getFlightInformation("1");
        assertEquals(flight, result);
    }

    @Test
    public void testGetAllFlightsIn24h() {
        Flight flight1 = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );
        Flight flight2 = new Flight(
                "2",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        Set<Flight> flights = new HashSet<>();
        flights.add(flight1);
        flights.add(flight2);

        when(flightsDao.getAll()).thenReturn(flights);

        Set<Flight> results = flightsService.getAllFlightsIn24h("Hongkong");
        assertTrue(results.contains(flight2));
        assertEquals(1, results.size());
    }

    @Test
    public void testGetRelevantFlights() {
        LocalDateTime fixedNow = LocalDateTime.of(2023, 8, 1, 10, 0);

        Flight relevantFlight = new Flight(
                "3",
                fixedNow.plusDays(3),
                fixedNow.plusDays(3).plusHours(12),
                new ArrayList<>(List.of("Paris", "Berlin")),
                200
        );

        Set<Flight> flights = new HashSet<>();
        flights.add(relevantFlight);

        when(flightsDao.getAll()).thenReturn(flights);

        LocalDateTime searchDepartureDate = fixedNow.plusDays(3);
        LocalDateTime searchArrivalDate = fixedNow.plusDays(3).plusHours(12);

        Set<Flight> results = flightsService.getRelevantFlights("Paris", "Berlin", searchDepartureDate, searchArrivalDate, 1);

        assertTrue(results.contains(relevantFlight));
        assertEquals(1, results.size());
    }

}
