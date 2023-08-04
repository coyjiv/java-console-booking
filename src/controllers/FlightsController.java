package controllers;

import services.Flights.FlightsService;

public class FlightsController {
    private FlightsService flightsService;

    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }
}
