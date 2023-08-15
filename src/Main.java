import controllers.*;
import daos.Booking.BookingDao;
import daos.Booking.FileBookingDao;
import daos.Flights.FlightsDao;
import daos.Session.SessionDao;
import daos.Users.UsersDao;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;
import services.Flights.FlightsService;
import services.Session.SessionService;
import services.Users.UsersService;

public class Main {
    public static void main(String[] args) {
        UsersDao usersDao = new UsersDao();
        UsersService usersService = new UsersService(usersDao);
        UsersController usersController = new UsersController(usersService);

        BookingDao bookingDao = new FileBookingDao();
        BookingService bookingService = new DefaultBookingService(bookingDao);
        BookingController bookingController = new BookingController(bookingService);

        FlightsDao flightsDao = new FlightsDao();
        FlightsService flightsService = new FlightsService(flightsDao);
        flightsService.generateRandomFlights(250);
        FlightsController flightsController = new FlightsController(flightsService, bookingController);

        SessionDao sessionsDao = new SessionDao();
        SessionService sessionsService = new SessionService(sessionsDao, usersService);
        SessionController sessionController = new SessionController(sessionsService);

        MainController main = new MainController(flightsController, bookingController, sessionController);

        main.run();

    }
}