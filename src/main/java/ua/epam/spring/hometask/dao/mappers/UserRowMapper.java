package ua.epam.spring.hometask.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dish on 24.10.17.
 */
public class UserRowMapper implements RowMapper<User> {
    
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("ID"));
        user.setFirstName(resultSet.getString("FIRST_NAME"));
        user.setLastName(resultSet.getString("LAST_NAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setBirthday(resultSet.getDate("BIRTHDAY"));
        return user;
    }
}
