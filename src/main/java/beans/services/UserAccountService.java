package beans.services;

import beans.models.UserAccount;

public interface UserAccountService {

    UserAccount getById(long userId);
    UserAccount refill(long userId,double amount);
    UserAccount withdraw(long userId, double ticketPrice);
}
