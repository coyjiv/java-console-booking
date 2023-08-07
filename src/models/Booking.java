package models;

public class Booking {
    private int ID;
    private Flight flight;
    private String passenger;
    private String seat;

    // Need to write logic for auto set ID
    public Booking(int ID, Flight flight, String passenger, String seat) {
        this.ID = ID;
        this.flight = flight;
        this.passenger = passenger;
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

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
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
        result = 31 * result + getPassenger().hashCode();
        result = 31 * result + getSeat().hashCode();
        return result;
    }
}
