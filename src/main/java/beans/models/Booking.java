package beans.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Booking {

    private long   id;
    private User   user;
    private Ticket ticket;

    public Booking(User user, Ticket ticket) {
        this(-1, user, ticket);
    }

    public Booking(long id, User user, Ticket ticket) {
        this.id = id;
        this.user = user;
        this.ticket = ticket;
    }

    public Booking withId(long id) {
        return new Booking(id, user, ticket);
    }
}
