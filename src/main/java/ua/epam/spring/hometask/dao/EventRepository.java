package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Created by dish on 02.10.17.
 */
public interface EventRepository {
    
    Event getByName(String name);
    
    Event save(Event object);
  
    void remove(Event object);
    
    Event getById(Long id);
    
    Collection<Event> getAll();
}
