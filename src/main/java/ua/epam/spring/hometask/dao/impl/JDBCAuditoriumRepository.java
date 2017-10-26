package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.AuditoriumRepository;
import ua.epam.spring.hometask.dao.mappers.AuditoriumRowMapper;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dish on 24.10.17.
 */

@Component
@Primary
public class JDBCAuditoriumRepository implements AuditoriumRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static String getByName = "SELECT * FROM AUDITORIUMS WHERE NAME = ?";
    private static String all = "SELECT * FROM AUDITORIUMS";
    private static  String allByName = "SELECT NAME FROM AUDITORIUMS";
    
    @Override
    public Set<Auditorium> getAll() {
        Set<Auditorium> auditoriums = new HashSet<> (jdbcTemplate.query(all, new BeanPropertyRowMapper(Auditorium.class)));
        return auditoriums;
    }
    
    @Override
    public Auditorium getByName(String name) {
        Auditorium auditorium = (Auditorium) jdbcTemplate.queryForObject(
            getByName, new Object[] { name }, new AuditoriumRowMapper());
        return auditorium;
    }
    
    @Override
    public Set<String> getAuditoriumNames() {
        Set<String> names = new HashSet<>();
        Set<Auditorium> auditoriums = new HashSet<>(jdbcTemplate.query(allByName, new BeanPropertyRowMapper(Auditorium.class)));
        for (Auditorium aud : auditoriums){
          names.add(aud.getName());
        }
        return names;
    }
}
