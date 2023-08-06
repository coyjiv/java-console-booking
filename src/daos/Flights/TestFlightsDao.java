package daos.Flights;


import models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFlightsDao {
    private FlightsDao flightsDao;

    @BeforeEach
    public void beforeEach(){
        flightsDao = new FlightsDao();
    }
    @Test
    public void testCreateFlight(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Honkong", "Kyiv")),
                200
        );

        flightsDao.create(flight);

        assertEquals(1, flightsDao.getAll().size());
        assertEquals(flightsDao.getFlightById("1"), flight);
    }
    @Test
    public void testGetAllFlightsEmpty(){
        assertEquals(new HashSet<Flight>(), flightsDao.getAll());
    }
    @Test
    public void testGetFlightById(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        flightsDao.create(flight);

        Flight foundFlight = flightsDao.getFlightById("1");
        assertEquals(flight, foundFlight);
    }
    @Test
    public void testGetUnexistingFlightById(){
        Flight foundFlight = flightsDao.getFlightById("1");
        assertNull(foundFlight);
    }
    @Test
    public void testEditFlightById(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        flightsDao.create(flight);

        Flight editedFlight = new Flight(
                "1",
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(2).plusHours(13),
                new ArrayList<>(List.of("Hongkong", "Kyiv", "Dubai")),
                250
        );

        boolean isEdited = flightsDao.edit("1", editedFlight);
        assertTrue(isEdited);
        assertEquals(editedFlight, flightsDao.getFlightById("1"));
    }
    @Test
    public void testEditFlightByIdWithNull(){
        boolean isEdited = flightsDao.edit("1", null);
        assertFalse(isEdited);
    }
    @Test
    public void testEditFlightWithFlight(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        flightsDao.create(flight);

        Flight editedFlight = new Flight(
                "1",
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(2).plusHours(13),
                new ArrayList<>(List.of("Hongkong", "Kyiv", "Dubai")),
                250
        );

        boolean isEdited = flightsDao.edit(flight, editedFlight);
        assertTrue(isEdited);
        assertEquals(editedFlight, flightsDao.getFlightById("1"));
    }
    @Test
    public void testDeleteFlightById(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        flightsDao.create(flight);
        boolean isDeleted = flightsDao.delete("1");

        assertTrue(isDeleted);
        assertNull(flightsDao.getFlightById("1"));
    }
    @Test
    public void testDeleteFlightByIdEqualsNull(){
        boolean isDeleted = flightsDao.delete("1");
        assertFalse(isDeleted);
    }

    @Test
    public void testDeleteFlightByFlightObj(){
        Flight flight = new Flight(
                "1",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(12),
                new ArrayList<>(List.of("Hongkong", "Kyiv")),
                200
        );

        flightsDao.create(flight);
        boolean isDeleted = flightsDao.delete(flight);

        assertTrue(isDeleted);
        assertNull(flightsDao.getFlightById("1"));
    }
}
