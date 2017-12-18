package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.daos.UserDAO;
import beans.exceptions.MyObjectNotFoundException;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Autowired
    UserDAO userDAO;

    /**
     * Updates user account balance
     * @param userId current user id
     * @param amount money to refill (can be + or - value)
     * @return UserAccount object
     */
    @Override
    public UserAccount refill(long userId, double amount) {
        User user=userDAO.get(userId);
        if(user==null){
            throw new MyObjectNotFoundException("User with id "+userId+" not found");
        }
        user.setBalance(user.getBalance()+amount);

        getCurrentSession().persist(user);

        return UserAccount.builder()
                .balance(user.getBalance())
                .userId(user.getId())
                .build();
    }

    @Override
    public UserAccount getById(long userId) {
        User user=userDAO.get(userId);
        if(user==null){
            throw new MyObjectNotFoundException("User with id "+userId+" not found");
        }

        return UserAccount.builder()
                .balance(user.getBalance())
                .userId(user.getId())
                .build();
    }
}
