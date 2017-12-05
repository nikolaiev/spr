package beans.daos;

import beans.models.Ticket;
import beans.models.User;

import java.util.List;

public interface TicketDAO {
    Ticket getById(Long id);

    List<Ticket> getAll();
}
