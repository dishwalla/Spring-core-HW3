package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.CounterRepository;
import ua.epam.spring.hometask.domain.Counter;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.*;

/**
 * Created by dish on 15.10.17.
 */

@Aspect
@Component
public class CounterAspect {
    
    @Autowired
    private CounterRepository counterRepository;

    @Before("execution(* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    public void countTicketsBooked(JoinPoint joinPoint) {
        Counter counter = new Counter();
        String name = "";
        Set<Ticket> tickets = (Set<Ticket>)joinPoint.getArgs()[0];
        for (Ticket t : tickets) {
            Integer ticketsBooked = 0;
            name = t.getEvent().getName();
            if (counterRepository.getByName(name) == null) {
                counter.setEvent(name);
                counter.setCounter(++ticketsBooked);
                counterRepository.increase(counter);
            } else
            if(counterRepository.getByName(name).getEvent().equals(name)){
                counterRepository.update(name);
            }
            
        }
        System.out.println("Tickets for "+ name + " have been booked for: " + counterRepository.getByName(name).getCounter() + " times");
    }

    @Before("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    public void countEventsByPrice(JoinPoint joinPoint) {
        Event event = (Event)joinPoint.getArgs()[0];
        Counter counter = new Counter();
        Integer cnt = counterRepository.getByName(event.getName()).getCounter();
        if (cnt == null) {
            cnt = 0;
            counter.setEvent(event.getName());
            counter.setCounter(++cnt);
            counterRepository.increase(counter);
        }
        counterRepository.update(event.getName());
        System.out.println("Event's prices for "+ event.getName() + " were queried: " + counterRepository.getByName(event.getName()).getCounter() + " times");
    }
    
}
