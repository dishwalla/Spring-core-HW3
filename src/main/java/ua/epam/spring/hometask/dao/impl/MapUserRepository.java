package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.UserRepository;
import ua.epam.spring.hometask.domain.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dish on 01.10.17.
 */

public class MapUserRepository implements UserRepository {
    
    private static Map<Long, User> users = new ConcurrentHashMap<>();
    
    public void save(User user){
        users.put(user.getId(), user);
    }
    
    public User getUserByEmail(String email) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
           if (user.getValue().getEmail() != null && email.equals(user.getValue().getEmail())){
               return (User) user;
           }
        }
        return null;
    }
    
    public void remove(User user) {
        users.remove(user);
    }
    
    public User getById(Long id) {
        return users.get(id);
    }
    
    public Collection<User> getAll() {
        return users.values();
    }
    
}
