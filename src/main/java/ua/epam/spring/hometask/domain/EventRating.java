package ua.epam.spring.hometask.domain;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {

    LOW,

    MID,

    HIGH;
    
    public static EventRating getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
