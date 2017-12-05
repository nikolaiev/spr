package beans.services;

import beans.daos.BookingDAO;
import beans.daos.TicketDAO;
import beans.daos.UserDAO;
import beans.exceptions.MyObjectNotFoundException;
import beans.models.Ticket;
import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    @Qualifier("bookingDAO")
    BookingDAO bookingDAO;

    @Autowired
    @Qualifier("ticketDAO")
    TicketDAO ticketDAO;

    @Autowired
    @Qualifier("userDAO")
    UserDAO userDAO;

    @Override
    public Ticket getTicketById(Long id) {
        return ticketDAO.getById(id);
    }

    @Override
    public List<Ticket> getTicketsByUser(Long userId) {
        User user=userDAO.get(userId);

        if(user==null){
            throw  new MyObjectNotFoundException("User not found "+userId);
        }
        return bookingDAO.getTickets(user);
    }
}
