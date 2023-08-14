package models;

import java.io.Serializable;
import java.util.Objects;

public class Password implements Serializable {
    String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Password password1 = (Password) o;

        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return password != null ? password.hashCode() : 0;
    }
}
