package controllers;

import services.Flights.FlightsService;

public class FlightsController {
    private final FlightsService flightsService;

    public FlightsController(FlightsService flightsService){
        this.flightsService = flightsService;
    }
}
