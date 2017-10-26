package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.CounterRepository;
import ua.epam.spring.hometask.dao.mappers.CounterRowMapper;
import ua.epam.spring.hometask.domain.Counter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dish on 26.10.17.
 */
@Component
public class JDBCBookedTicketsCounterRepository implements CounterRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    
    private static String increase = "INSERT INTO BOOKED_TICKETS_COUNTERS (EVENT, COUNT) VALUES(:event, :count);";
    private static String updateCount = "UPDATE BOOKED_TICKETS_COUNTERS  SET COUNT = COUNT + 1 WHERE EVENT =?";
    private static String getByEvent = "SELECT * FROM BOOKED_TICKETS_COUNTERS  WHERE EVENT = ?";
    
    @Override
    public void increase(Counter counter) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("event", counter.getEvent());
        parameters.put("count", counter.getCounter());
        jdbcNamedTemplate.update(increase, parameters);
    }

    @Override
    public void update(String event) {
        jdbcTemplate.update(updateCount, new Object[] { event });
    }
    
    @Override
    public Counter getByName(String name) {
        try {
        Counter counter = (Counter)jdbcTemplate. queryForObject(
            getByEvent, new Object[] {name}, new CounterRowMapper());
            return counter;
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
