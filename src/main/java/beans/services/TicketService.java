package beans.services;


import beans.models.Ticket;

import java.util.List;

public interface TicketService {

    Ticket getTicketById(Long id);
    List<Ticket> getTicketsByUser(Long userId);
}
