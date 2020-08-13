package ru.job4j.cinema.service;

import ru.job4j.cinema.persistence.Accounts;
import ru.job4j.cinema.persistence.Halls;

import java.util.Collection;

/**
 * ru.job4j.cinema.persistence && ru.job4j.cinema.persistence
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public interface Store {
    Halls createHall(Halls seat);

    Accounts createAccount(Accounts account);

    Collection<Integer> findSeatsFromHalls();
}
