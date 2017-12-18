package beans.daos;

import beans.models.UserAccount;

public interface UserAccountDAO {

    UserAccount refill(long userId, double amount);

    UserAccount getById(long userId);
}
