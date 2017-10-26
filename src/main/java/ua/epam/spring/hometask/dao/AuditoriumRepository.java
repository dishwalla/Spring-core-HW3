package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * Created by dish on 04.10.17.
 */
public interface AuditoriumRepository {
    
    Set<Auditorium> getAll();
    
    Auditorium getByName(String name);
    
    Set<String> getAuditoriumNames();
}
