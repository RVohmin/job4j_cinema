package ru.job4j.cinema.service;

import java.util.Objects;

/**
 * ru.job4j.cinema.service
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class Halls {
    private String row;
    private String seat;
    private int userID;

    public Halls(String row, String seat, int userID) {
        this.row = row;
        this.seat = seat;
        this.userID = userID;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Halls halls = (Halls) o;
        return getUserID() == halls.getUserID()
                && Objects.equals(getRow(), halls.getRow())
                && Objects.equals(getSeat(), halls.getSeat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getSeat(), getUserID());
    }
}
