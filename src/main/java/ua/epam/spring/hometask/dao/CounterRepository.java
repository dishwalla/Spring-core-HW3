package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Counter;

/**
 * Created by dish on 26.10.17.
 */
public interface CounterRepository {
     
     void increase(Counter counter);
     void update(String event);
     Counter getByName(String event);
}
