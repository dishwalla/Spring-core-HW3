package ua.epam.spring.hometask.service.strategies;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.util.Date;

/**
 * Created by dish on 10.10.17.
 */

@Component
public class BirthdayStrategy implements DiscountStrategy {
    
    public static final int DISCOUNT_PERCENT = 5;
    public static int FIVE_DAYS = 5*24*60*60*1000;
    @Override
    public int getDiscountPercentage(User user, Event event, Date airDateTime, long numberOfTickets) {
        Date bd = user.getBirthday();
        if (Math.abs(airDateTime.getTime() - bd.getTime()) < FIVE_DAYS){
            return DISCOUNT_PERCENT;
        }
        return 0;
    }
}
