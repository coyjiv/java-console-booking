package daos.Booking;

import models.Booking;
import models.Flight;

import java.io.*;
import java.util.*;

public class FileBookingDao implements BookingDao {
    private final Set<Booking> bookings = new HashSet<>();
    private static final String BOOKINGS_FILE_NAME = "bookings.ser";

    public FileBookingDao() {
        loadBookings();
    }

    @Override
    public Set<Booking> getAll() {
        return this.bookings;
    }

    @Override
    public Booking getBookingById(int ID) throws NoSuchElementException {
        Optional<Booking> book = getAll().stream().filter(booking -> booking.getID() == ID).findFirst();

        if (book.isPresent()) {
            return (Booking) book.get();
        } else {
            throw new NoSuchElementException("Бронювання за таким ID не існує !");
        }
    }

    @Override
    public void create(Booking book) {
        this.bookings.add(book);
        saveBookings();
    }

    @Override
    public boolean cancel(int ID) {
        boolean result = this.bookings.remove(getBookingById(ID));
        if (result) {
            saveBookings();
        }
        return result;
    }

    @Override
    public boolean cancel(Booking booking) {
        boolean result = this.bookings.remove(booking);
        if (result) {
            saveBookings();
        }
        return result;
    }

    private void saveBookings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKINGS_FILE_NAME))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBookings() {
        File file = new File(BOOKINGS_FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object readObject = ois.readObject();
                if (readObject instanceof Set) {
                    bookings.addAll((Set<Booking>) readObject);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
