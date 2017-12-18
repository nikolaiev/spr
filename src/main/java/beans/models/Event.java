package beans.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Event {

    private long          id;
    private String        name;
    private Rate          rate;
    private double ticketPrice;
    private LocalDateTime dateTime;
    private Auditorium    auditorium;


    public Event(String name, Rate rate, double ticketPrice, LocalDateTime dateTime, Auditorium auditorium) {
        this(-1, name, rate, ticketPrice, dateTime, auditorium);
    }

    public Event(long id, String name, Rate rate, double ticketPrice, LocalDateTime dateTime, Auditorium auditorium) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.ticketPrice = ticketPrice;
        this.dateTime = dateTime;
        this.auditorium = auditorium;
    }

    public Event withId(Long eventId) {
        return new Event(eventId, this.name, this.rate, this.ticketPrice, this.dateTime, this.auditorium);
    }
}
