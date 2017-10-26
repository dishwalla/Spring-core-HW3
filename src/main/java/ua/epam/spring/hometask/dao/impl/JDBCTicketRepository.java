package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.IdGenerator;
import ua.epam.spring.hometask.dao.TicketRepository;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dish on 24.10.17.
 */

@Component
@Primary
public class JDBCTicketRepository implements TicketRepository {
    
    private static String insertTicket = "INSERT INTO TICKETS(ID, USER, EVENT, THE_DATE, SEAT) VALUES(:id, :user, :event, :date, :seat);";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    
    @Override
    public void saveTickets(Set<Ticket> tickets) {
        for(Ticket ticket : tickets){
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("id", IdGenerator.getNextTicketId());
            parameters.put("user", ticket.getUser().getFirstName());
            parameters.put("event", ticket.getEvent().getName());
            parameters.put("date", ticket.getDateTime());
            parameters.put("seat", ticket.getSeat());
            jdbcNamedTemplate.update(insertTicket, parameters);
        }
    }
    
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, Date dateTime) {
//        Set<Ticket> ticketSet = tickets.values().stream()
//            .filter(t -> event.equals(t.getEvent()) && dateTime.equals(t.getDateTime()))
//            .collect(Collectors.toSet());
//        return ticketSet;
        return null;
    }
}
