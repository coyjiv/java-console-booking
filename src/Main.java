import controllers.BookingsController;
import controllers.FlightsController;
import controllers.MainController;
import daos.Booking.BookingDao;
import daos.Booking.SetBookingDao;
import daos.Flights.FlightsDao;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;
import services.Flights.FlightsService;

public class Main {
    public static void main(String[] args) {
        BookingDao bookingDao = new SetBookingDao();
        BookingService bookingService = new DefaultBookingService(bookingDao);
        BookingsController bookingsController = new BookingsController(bookingService);

        FlightsDao flightsDao = new FlightsDao();
        FlightsService flightsService = new FlightsService(flightsDao);
        FlightsController flightsController = new FlightsController(flightsService);

        MainController main = new MainController(flightsController, bookingsController);

        main.run();

    }
}