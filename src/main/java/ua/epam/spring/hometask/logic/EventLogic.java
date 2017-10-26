package ua.epam.spring.hometask.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.IdGenerator;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImpl;
import ua.epam.spring.hometask.service.impl.EventServiceImpl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dish on 01.10.17.
 */
@Component
public class EventLogic {
    
    public static final int DAY_MILLIS = 1000 * 60 * 60 * 24;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private AuditoriumService auditoriumService;
    
    private static final List<Double> basePrices = new LinkedList<>(Arrays.asList(10.0, 15.0, 20.0, 25.0, 30.0, 35.0, 45.0, 50.0));
    private static final List<String> names = new LinkedList<>(Arrays.asList("Film1","Film2","Film3","Film4","Film5","Film6","Film7","Film8","Film9","Film10"));
    private static Map<String, EventRating> ratings = new ConcurrentHashMap<>();
    
    public EventLogic(){
        setRating();
    }
  
   public EventService getEventService() {
        return eventService;
    }
    
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    
    public AuditoriumService getAuditoriumService() {
        return auditoriumService;
    }
    
    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }
    
    public void showEvents(){
        for(Event event : eventService.getAll()) {
            System.out.println(event.toString());
        }
    }
    
    public void eventGenerator(){
        
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setId((long) IdGenerator.getNextEventId());
            event.setName(getName());
            event.setBasePrice(getPrice());
            event.setRating(ratings.get(event.getName()));
            Date now = new Date();
            Date airDate = new Date(now.getTime() + DAY_MILLIS * getRandomInt(10));
            event.addAirDateTime(airDate);
            NavigableMap<Date, Auditorium> auditoriums = new TreeMap<>();
            Auditorium auditorium = auditoriumService.getRandomAuditorium();
            auditoriums.put(airDate, auditorium);
            event.setAuditoriums(auditoriums);
            event.setAuditoriumName(auditorium.getName());
            eventService.save(event);
        }
    }
    
    protected double generateId(){
        int i;
        int g = 1000;
        double randomNum = 0.0;
        Random rn = new Random();
        for (i = 0; i < g; i++) {
            randomNum = rn.nextDouble();
            if(!basePrices.contains(randomNum)){
                basePrices.add(randomNum);
                return randomNum;
            } else i++;
        }
        return randomNum;
    }
    protected double getPrice(){
        Random rn = new Random();
        int randomNum = rn.nextInt(basePrices.size());
        double price = 00.0;
        for (int i = 0; i < basePrices.size(); i++) {
            price = basePrices.get(randomNum);
            return price;
        }
        return price;
    }
    
    protected String getName(){
        Random rn = new Random();
        String name = "";
        Collections.shuffle(names, rn);
        for (int i = 0; i < names.size(); i++) {
            return names.iterator().next();
        }
        return name;
    }

    protected void setRating(){
        for(String film : names){
            ratings.put(film, EventRating.getRandom());
        }
    }
    
    protected int getRandomInt(int a){
        Random rn = new Random();
        return rn.nextInt(a);
    }

}
