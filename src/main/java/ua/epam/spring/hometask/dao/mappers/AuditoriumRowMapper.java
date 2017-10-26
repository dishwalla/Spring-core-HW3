package ua.epam.spring.hometask.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.Auditorium;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dish on 24.10.17.
 */
public class AuditoriumRowMapper implements RowMapper<Auditorium> {
    
    @Override
    public Auditorium mapRow(ResultSet resultSet, int i) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(resultSet.getString("NAME"));
        auditorium.setNumberOfSeats(resultSet.getLong("NUMBER_OF_SEATS"));
        Set<Long> mySet = new HashSet(Arrays.asList(resultSet.getString("VIP_SEATS").split(",")));
        auditorium.setVipSeats(mySet);
        return auditorium;
    }
}
