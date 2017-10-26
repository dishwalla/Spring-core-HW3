package ua.epam.spring.hometask.domain;

import java.util.Date;
import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class Ticket extends DomainObject implements Comparable<Ticket> {
    
    public Ticket() {}
    
    public Ticket(User user, Event event, Date dateTime, long seat) {
        this.user = user;
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
    }
    private User user;
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    public void setSeat(long seat) {
        this.seat = seat;
    }
    
    private Event event;
    private Date dateTime;
    private long seat;
    private String eventId;
    private String userId;
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public long getSeat() {
        return seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, event, seat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket other = (Ticket) obj;
        if (dateTime == null) {
            if (other.dateTime != null) {
                return false;
            }
        } else if (!dateTime.equals(other.dateTime)) {
            return false;
        }
        if (event == null) {
            if (other.event != null) {
                return false;
            }
        } else if (!event.equals(other.event)) {
            return false;
        }
        if (seat != other.seat) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = dateTime.compareTo(other.getDateTime());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }

}
