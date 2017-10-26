package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

/**
 * Created by dish on 01.10.17.
 */
public interface UserRepository {
    
    void save(User user);
    
    User getUserByEmail(String email);
    
    void remove(User user);
    
    User getById(Long id);
    
    Collection<User> getAll();
}
