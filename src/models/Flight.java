package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Flight implements Serializable {
    private String id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private ArrayList<String> route;
    private int totalSeats;

    public Flight(String id, LocalDateTime departureDate, LocalDateTime estimatedArrivalDate, ArrayList<String> locations, int totalSeats) {
        this.id = id;
        this.departureDate = departureDate;
        this.arrivalDate = estimatedArrivalDate;
        this.route = locations;
        this.totalSeats = totalSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDate(LocalDateTime date) {
        this.departureDate = date;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public ArrayList<String> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<String> route) {
        this.route = route;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getDepartureCity(){
        return route.isEmpty()?null: route.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return getId().equals(flight.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %-20s | %-50s |",
                getId(),
                getDepartureDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                getArrivalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                String.join(" -> ", getRoute()));
    }

}
