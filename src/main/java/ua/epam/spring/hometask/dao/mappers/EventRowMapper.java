package ua.epam.spring.hometask.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by dish on 24.10.17.
 */
public class EventRowMapper implements RowMapper<Event> {
    
    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong("ID"));
        event.setName(resultSet.getString("NAME"));
        event.setBasePrice(resultSet.getLong("BASE_PRICE"));
        event.setRating(EventRating.valueOf(resultSet.getString("RAITING")));
        Set<Date> mySet = new TreeSet(Arrays.asList(resultSet.getString("AIR_DATE").split(",")));
        event.setAirDates((NavigableSet<Date>)mySet);
        event.setAuditoriumName(resultSet.getString("AUDITORIUM"));
        return event;
    }
}
