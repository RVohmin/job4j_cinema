package ru.job4j.cinema.service;

import java.util.Objects;

/**
 * ru.job4j.cinema.service
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class Accounts {
    private String username;
    private String phone;

    public Accounts(String username, String phone) {
        this.username = username;
        this.phone = phone;
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
