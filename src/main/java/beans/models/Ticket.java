package beans.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.CsvUtil;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Ticket {

    private long          id;
    private Event         event;
    private LocalDateTime dateTime;
    private String        seats;
    private User          user;
    private Double        price;

    public Ticket(Event event, LocalDateTime dateTime, List<Integer> seats, User user, double price) {
        this(-1, event, dateTime, seats, user, price);
    }

    public Ticket(long id, Event event, LocalDateTime dateTime, List<Integer> seats, User user, Double price) {
        this(id, event, dateTime, CsvUtil.fromListToCsv(seats), user, price);
    }

    public Ticket(long id, Event event, LocalDateTime dateTime, String seats, User user, Double price) {
        this.id = id;
        this.event = event;
        this.dateTime = dateTime;
        this.user = user;
        this.price = price;
        this.seats = seats;
    }


    public void setSeatsList(List<Integer> seats) {
        this.seats = CsvUtil.fromListToCsv(seats);
    }

    public List<Integer> getSeatsList() {
        return CsvUtil.fromCsvToList(seats, Integer:: valueOf);
    }

    public Ticket withId(Long ticketId) {
        return new Ticket(ticketId, event, dateTime, seats, user, price);
    }
}
