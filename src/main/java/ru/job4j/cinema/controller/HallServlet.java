package ru.job4j.cinema.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ru.job4j.cinema.service.PSQLStore;
import ru.job4j.cinema.persistence.Accounts;
import ru.job4j.cinema.persistence.Halls;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * ru.job4j.cinema.controller
 *
 * @author romanvohmin
 * @since 10.08.2020
 */
public class HallServlet extends HttpServlet {
    public final static Logger LOGGER = Logger.getLogger(HallServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.debug("in doGet ");
        List<Integer> list = new ArrayList<>(PSQLStore.instOf().findSeatsFromHalls());
        String json = new Gson().toJson(list);
        LOGGER.debug(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        out.println(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("name");
        LOGGER.info("Servlet doPost: userName - " + userName);
        String phone = req.getParameter("phone");
        LOGGER.info("Servlet doPost: phone - " + phone);
        String value = req.getParameter("seat");
        LOGGER.info("Servlet doPost: seat - : " + value);
        Halls hall = PSQLStore.instOf().createHall(new Halls(0, Integer.parseInt(value)));
        LOGGER.debug("hall.getId()" + hall.getId());
        Accounts account = PSQLStore.instOf().createAccount(
                new Accounts(0, userName, phone, hall.getId())
        );
        LOGGER.debug(account);
    }
}
