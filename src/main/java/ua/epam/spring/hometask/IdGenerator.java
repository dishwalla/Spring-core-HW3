package ua.epam.spring.hometask;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dish on 02.10.17.
 */
@Component
public class IdGenerator {
    
    public static AtomicInteger userID = new AtomicInteger(0);
    public static AtomicInteger ticketID = new AtomicInteger(0);
    public static AtomicInteger eventID = new AtomicInteger(0);
    public static AtomicInteger auditoriumID = new AtomicInteger(0);
    
    public static int getNextUserId(){return userID.incrementAndGet();
    }
    public static int getNextTicketId(){
        return ticketID.incrementAndGet();
    }
    public static int getNextEventId(){
        return eventID.incrementAndGet();
    }
    public final static Integer getNextAuditoriumId(){return auditoriumID.incrementAndGet();
    }
}
