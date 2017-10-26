package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.IdGenerator;
import ua.epam.spring.hometask.dao.TicketRepository;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by dish on 01.10.17.
 */

public class MapTicketRepository implements TicketRepository {
    
    private static Map<Integer, Ticket> tickets = new ConcurrentHashMap<>();
    
    @Override
    public void saveTickets(Set<Ticket> t) {
        t.stream().forEach(ticket -> {
            tickets.put(IdGenerator.getNextTicketId(), ticket);
        });
    }
    
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, Date dateTime) {
        Set<Ticket> ticketSet = tickets.values().stream()
            .filter(t -> event.equals(t.getEvent()) && dateTime.equals(t.getDateTime()))
            .collect(Collectors.toSet());
        return ticketSet;
    }
}
