package ua.epam.spring.hometask.service.strategies;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.util.Date;

/**
 * Created by dish on 09.10.17.
 */
public interface DiscountStrategy {
    int getDiscountPercentage(User user, Event event, Date airDateTime, long numberOfTickets);
}
