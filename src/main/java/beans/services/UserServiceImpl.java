package beans.services;

import beans.daos.TicketDAO;
import beans.daos.UserDAO;
import beans.models.MyUserPrincipal;
import beans.models.Ticket;
import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final TicketDAO ticketDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO,
                           @Qualifier("ticketDAO") TicketDAO ticketDAO) {
        this.userDAO = userDAO;
        this.ticketDAO= ticketDAO;
    }

    //used in tests
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.ticketDAO=null;
    }

    public User register(User user) {
        return userDAO.create(user);
    }

    public void remove(User user) {
        userDAO.delete(user);
    }

    public User getById(long id) {
        return userDAO.get(id);
    }

    public User getUserByEmail(String email) {
            return userDAO.getByEmail(email);
        }

    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    public List<Ticket> getBookedTickets() {
        return ticketDAO.getAll();
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void register(User user, double balance) {
        user.setBalance(balance);
        System.out.println(user);
        userDAO.create(user);
    }

    /*UserDetailsService*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= getUserByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("User with email "+email+" not found");
        }

        return new MyUserPrincipal(user.getPassword(),user.getEmail(),user.getRoles());
    }
}
