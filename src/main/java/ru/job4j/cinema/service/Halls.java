package ru.job4j.cinema.service;

import java.util.Objects;

/**
 * ru.job4j.cinema.service
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class Halls {
    private int id;
    private int seat;

    public Halls(int id, int seat) {
        this.seat = seat;
        this.id = id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return getId() == halls.getId()
                && Objects.equals(getSeat(), halls.getSeat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeat(), getId());
    }
}
