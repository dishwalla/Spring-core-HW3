package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.EventRepository;
import ua.epam.spring.hometask.domain.Event;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dish on 02.10.17.
 */

public class MapEventRepository implements EventRepository {
    
    private static Map<Long, Event> events = new ConcurrentHashMap<>();
    
    @Override
    public Event getByName(String name) {
        for (Map.Entry<Long, Event> event : events.entrySet()) {
            if (event.getValue().getName() != null && name.equals(event.getValue().getName())){
                return (Event) event;
            }
        }
        return null;
    }
    
    @Override
    public Event save(Event event) {
        return events.put(event.getId(), event);
    }
    
    @Override
    public void remove(Event event) {
        events.remove(event);
    }
    
    @Override
    public Event getById(Long id) {
        return events.get(id);
    }
    
    @Override
    public Collection<Event> getAll() {
        return events.values();
    }

}
