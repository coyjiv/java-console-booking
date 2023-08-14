import controllers.*;
import daos.Booking.BookingDao;
import daos.Booking.SetBookingDao;
import daos.Flights.FlightsDao;
import daos.Sessions.SessionsDao;
import daos.Users.UsersDao;
import services.Booking.BookingService;
import services.Booking.DefaultBookingService;
import services.Flights.FlightsService;
import services.Session.SessionsService;
import services.Users.UsersService;

public class Main {
    public static void main(String[] args) {
        BookingDao bookingDao = new SetBookingDao();
        BookingService bookingService = new DefaultBookingService(bookingDao);
        BookingsController bookingsController = new BookingsController(bookingService);

        FlightsDao flightsDao = new FlightsDao();
        FlightsService flightsService = new FlightsService(flightsDao);
        FlightsController flightsController = new FlightsController(flightsService);

        UsersDao usersDao = new UsersDao();
        UsersService usersService = new UsersService(usersDao);
        UsersController usersController = new UsersController(usersService);

        SessionsDao sessionsDao = new SessionsDao();
        SessionsService sessionsService = new SessionsService(sessionsDao);
        SessionController sessionController = new SessionController(sessionsService,usersController);

        MainController main = new MainController(flightsController, bookingsController,sessionController);

        main.run();

    }
}