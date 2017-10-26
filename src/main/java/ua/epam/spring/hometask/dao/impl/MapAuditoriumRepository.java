package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.AuditoriumRepository;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dish on 04.10.17.
 */

public class MapAuditoriumRepository implements AuditoriumRepository {
    
    @Resource(name = "auditoriumMap")
    private Map<String, Auditorium> auditoriums;
    
    
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriums.values());
    }
    
    @Override
    public Auditorium getByName(String name) {
        Auditorium auditorium = null;
        for (Map.Entry auditoriumEntry : auditoriums.entrySet()) {
            auditorium = (Auditorium)auditoriumEntry.getValue();
           if (name.equals(auditorium.getName())) {
                return auditorium;
            }
        }
        return auditorium;
    }
    
    @Override
    public Set<String> getAuditoriumNames(){
        return auditoriums.keySet();
    }
}
