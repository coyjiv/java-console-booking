package services.Flights;

import daos.Flights.FlightsDao;
import models.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestFlightsService {
    @Mock
    private FlightsDao flightsDao;
    @InjectMocks
    private FlightsService flightsService;

    @Test
    public void testDisplayFlightInformation() {
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        when(flightsDao.getFlightById("1")).thenReturn(flight);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        flightsService.displayFlightInformation("1");

        String expectedOutput = flight.toString() + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayAllFlightsIn24h() {
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

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        flightsService.displayAllFlightsIn24h("Hongkong");

        String expectedOutput = flight2.toString() + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

}
