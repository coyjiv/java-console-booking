package models;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Booking implements Serializable {
    private int ID;
    private Flight flight;
    private String seat;

    public Booking(int ID, Flight flight, String seat) {
        this.ID = ID;
        this.flight = flight;
        this.seat = seat;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        return getID() == booking.getID();
    }
    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getFlight().hashCode();
        result = 31 * result + getSeat().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("| id: %d | Departure Date:  %-20s | Arrival date : %-20s | Route: %-50s |",
                getID(),
                flight.getDepartureDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                flight.getArrivalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
                String.join(" -> ", flight.getRoute()));
    }
}