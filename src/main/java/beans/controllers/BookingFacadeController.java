package beans.controllers;

import beans.daos.UserAccountDAO;
import beans.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Controller
@RequestMapping(value = "/account")
public class BookingFacadeController implements BookingFacade {
    @Autowired
    UserAccountDAO userAccountDAO;

    @Override
    @Transactional(isolation = READ_COMMITTED)
    @RequestMapping(value = "/refill/{user_id}/{money_amount}",method = RequestMethod.POST)
    public void fillAccount(@PathVariable("user_id") long userId,@PathVariable("money_amount") double moneyAmount) {
        userAccountDAO.refill(userId,moneyAmount);
    }
}
