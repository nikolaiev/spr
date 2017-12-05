package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.TicketDAO;
import beans.models.Ticket;
import beans.models.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "ticketDAO")
public class TicketDAOImpl extends AbstractDAO implements TicketDAO {

    @Override
    public Ticket getById(Long id) {
        return (Ticket) createBlankCriteria(Ticket.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public List<Ticket> getAll() {
        return ((List<Ticket>) createBlankCriteria(Ticket.class).list());
    }
}
