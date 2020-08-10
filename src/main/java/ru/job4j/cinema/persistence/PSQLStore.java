package ru.job4j.cinema.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

/**
 * ru.job4j.cinema.persistence
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class PSQLStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();
    public final static Logger LOGGER = Logger.getLogger(JDBCTransactions.class);

    private PSQLStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
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
}
