package ua.epam.spring.hometask.dao.mappers;


import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dish on 24.10.17.
 */
public class TicketRowMapper implements RowMapper<Ticket> {
    
    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("ID"));
        ticket.setUserId(resultSet.getString("USER"));
        ticket.setEventId(resultSet.getString("EVENT"));
        ticket.setDateTime(resultSet.getDate("THE_DATE"));
        ticket.setSeat(resultSet.getInt("SEAT"));
        return ticket;
    }
}
