package ua.epam.spring.hometask.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.Counter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dish on 26.10.17.
 */
public class CounterRowMapper implements RowMapper<Counter> {
    
    @Override
    public Counter mapRow(ResultSet resultSet, int i) throws SQLException {
        Counter counter = new Counter();
        counter.setEvent(resultSet.getString("EVENT"));
        counter.setCounter(resultSet.getInt("COUNT"));
        return counter;
    }
}
