package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private String name;
    private String surname;
    private Login login;
    private Password password;
    private  Set<Booking> bookings = new HashSet<>();

    public User(String name, String surname, Login login, Password password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }
}