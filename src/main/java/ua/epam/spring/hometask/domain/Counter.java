package ua.epam.spring.hometask.domain;

/**
 * Created by dish on 26.10.17.
 */
public class Counter extends DomainObject{
    
    private String event;
    private int counter;
    
    public String getEvent() {
        return event;
    }
    
    public void setEvent(String event) {
        this.event = event;
    }
    
    public int getCounter() {
        return counter;
    }
    
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
