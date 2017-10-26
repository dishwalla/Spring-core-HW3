package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * Created by dish on 01.10.17.
 */
public interface TicketRepository {

     void saveTickets(Set<Ticket> tickets);
    
     Set<Ticket> getPurchasedTicketsForEvent(Event event, Date dateTime);
}
