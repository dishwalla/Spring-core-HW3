package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.strategies.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by dish on 01.10.17.
 */
@Component
public class DiscountServiceImpl implements DiscountService {
    
    @Autowired
    private DiscountStrategy discountStrategy;
    
    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }
    
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    
    @Override
    public byte getDiscount(User user, Event event, Date airDateTime, long numberOfTickets) {
        return (byte)discountStrategy.getDiscountPercentage(user, event, airDateTime, numberOfTickets);
    }
}
