package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.EventRepository;
import ua.epam.spring.hometask.dao.mappers.EventRowMapper;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;

import java.util.*;

/**
 * Created by dish on 24.10.17.
 */

@Component
@Primary
public class JDBCEventRepository implements EventRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    
    private static String getByName = "SELECT * FROM EVENTS WHERE NAME = ?";
    private static String getById = "SELECT * FROM EVENTS WHERE ID = ?";
    private static String all = "SELECT * FROM EVENTS";
    private static String insertEvent = "INSERT INTO EVENTS(ID, NAME, BASE_PRICE, RAITING, AIR_DATE, AUDITORIUM) VALUES(:id, :name, :price, :raiting, :air, :aud);";
    private static String delete = "DELETE FROM EVENTS WHERE ID = ?";
    
    @Override
    public Event getByName(String name) {
        Event event = (Event)jdbcTemplate.queryForObject(
            getByName, new Object[] { name}, new EventRowMapper());
        return event;
    }
    
    @Override
    public Event save(Event event) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", event.getId());
        parameters.put("name", event.getName());
        parameters.put("price", event.getBasePrice());
        parameters.put("raiting", event.getRating().toString());
        parameters.put("air", event.getAirDates());
        NavigableMap<Date, Auditorium> map = event.getAuditoriums();
        Auditorium auditorium = null;
        String name = "";
        for (Map.Entry auditoriumEntry : map.entrySet()) {
            auditorium = (Auditorium)auditoriumEntry.getValue();
            if (map.size() == 1) {
                name = auditorium.getName();
            }
        }
        parameters.put("aud", name);
        jdbcNamedTemplate.update(insertEvent, parameters);
        return event;
        
    }
    
    @Override
    public void remove(Event event) {
        jdbcTemplate.update(delete, event.getId());
    }
    
    @Override
    public Event getById(Long id) {
        Event event = (Event)jdbcTemplate.queryForObject(
            getById, new Object[] { id }, new EventRowMapper());
        return event;
    }
    
    @Override
    public Collection<Event> getAll() {
        List<Event> events = jdbcTemplate.query(all, new BeanPropertyRowMapper(Event.class));
        return events;
    }
}
