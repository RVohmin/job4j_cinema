package ru.job4j.cinema.service;

import java.util.Objects;

/**
 * ru.job4j.cinema.service
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class Accounts {
    private int id;
    private String username;
    private String phone;
    private int seatId;

    public Accounts(int id, String username, String phone, int seatId) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.seatId = seatId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Accounts{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", phone='" + phone + '\''
                + ", seatId=" + seatId
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accounts accounts = (Accounts) o;
        return Objects.equals(getUsername(), accounts.getUsername())
                && Objects.equals(getPhone(), accounts.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPhone());
    }
}
