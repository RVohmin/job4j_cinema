package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.service.Accounts;
import ru.job4j.cinema.service.Halls;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * ru.job4j.cinema.persistence
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class PSQLStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();

    private PSQLStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("/Users/romanvohmin/projects/job4j_cinema/db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            System.out.println("Driver Error");
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);

    }

    private static final class Lazy {
        private static final Store INST = new PSQLStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

//    public Collection<Halls> findAllHalls() {
//        List<Halls> halls = new ArrayList<>();
//        try (Connection cn = pool.getConnection();
//             PreparedStatement ps = cn.prepareStatement("SELECT * FROM halls")
//        ) {
//            try (ResultSet it = ps.executeQuery()) {
//                while (it.next()) {
//                    halls.add(new Halls(it.getInt("id"), it.getInt("seat")));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return halls;
//    }

    @Override
    public Collection<Integer> findSeatsFromHalls() {
        Collection<Integer> seats = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM halls")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    seats.add(it.getInt("seat"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seats;

    }

    @Override
    public Halls createHall(Halls seat) {
        Savepoint saveObj = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("insert into halls(seat) values (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            try {
                cn.setAutoCommit(false);
                saveObj = cn.setSavepoint();
                ps.setInt(1, seat.getSeat());
                ps.execute();
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        seat.setId(id.getInt(1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cn.commit();
            } catch (Exception e) {
                cn.rollback(saveObj);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seat;
    }

    @Override
    public Accounts createAccount(Accounts account) {
        Savepoint saveObj = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO accounts(name, phone, seat_id) VALUES (?, ?, ?) ",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            try {
                cn.setAutoCommit(false);
                saveObj = cn.setSavepoint();
                ps.setString(1, account.getUsername());
                ps.setString(2, account.getPhone());
                ps.setInt(3, account.getSeatId());
                ps.execute();
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        account.setId(id.getInt(1));
                    }
                }
                cn.commit();
            } catch (Exception e) {
                cn.rollback(saveObj);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
}