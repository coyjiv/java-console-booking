import controllers.BookingController;
import controllers.FlightsController;
import controllers.MainController;
import daos.Booking.BookingDao;
import daos.Booking.FileBookingDao;
import daos.Flights.FlightsDao;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;
import services.Flights.FlightsService;

public class Main {
    public static void main(String[] args) {
        BookingDao bookingDao = new FileBookingDao();
        BookingService bookingService = new DefaultBookingService(bookingDao);
        BookingController bookingController = new BookingController(bookingService);

        FlightsDao flightsDao = new FlightsDao();
        FlightsService flightsService = new FlightsService(flightsDao);
        FlightsController flightsController = new FlightsController(flightsService, bookingController);

        MainController main = new MainController(flightsController, bookingController);

        main.run();

    }
}