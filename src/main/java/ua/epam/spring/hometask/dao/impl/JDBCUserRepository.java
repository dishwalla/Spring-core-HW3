package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.UserRepository;
import ua.epam.spring.hometask.dao.mappers.UserRowMapper;
import ua.epam.spring.hometask.domain.User;

import java.util.*;

/**
 * Created by dish on 24.10.17.
 */

@Component
@Primary
public class JDBCUserRepository implements UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    
    private static String getByEmail = "SELECT * FROM USERS WHERE EMAIL = ?";
    private static String getById = "SELECT * FROM USERS WHERE ID = ?";
    private static String all = "SELECT * FROM USERS";
    private static String insertUser = "INSERT INTO USERS(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, EMAIL) VALUES(:id, :fname, :lname, :bd, :email);";
    private static String delete = "DELETE FROM USERS WHERE ID = ?";
    
    @Override
    public void save(User user) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", user.getId());
        parameters.put("fname", user.getFirstName());
        parameters.put("lname", user.getLastName());
        parameters.put("bd", user.getBirthday());
        parameters.put("email", user.getEmail());
        jdbcNamedTemplate.update(insertUser, parameters);
    }
    
    @Override
    public User getUserByEmail(String email) {
        User user = (User)jdbcTemplate.queryForObject(
            getByEmail, new Object[] { email }, new UserRowMapper());
        return user;
    }
    
    @Override
    public void remove(User user) {
        jdbcTemplate.update(delete, user.getId());
    }
    
    @Override
    public User getById(Long id) {
        User user = (User)jdbcTemplate.queryForObject(
            getById, new Object[] { id }, new UserRowMapper());
        return user;
    }
    
    @Override
    public Collection<User> getAll() {
        List<User> users = jdbcTemplate.query(all, new BeanPropertyRowMapper(User.class));
        return users;
    }
}
