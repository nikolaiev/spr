package beans.services;

import beans.daos.UserAccountDAO;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountDAO userAccountDAO;

    @Override
    public UserAccount getById(long userId) {
        return userAccountDAO.getById(userId);
    }

    @Override
    public UserAccount refill(long userId, double amount) {
        return userAccountDAO.refill(userId,amount);
    }

    @Override
    public UserAccount withdraw(long userId, double ticketPrice) {
        return refill(userId, -ticketPrice);
    }
}
