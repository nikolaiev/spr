package beans.controllers;

import beans.exceptions.MyNotEnoughMoneyException;
import beans.exceptions.MyObjectNotFoundException;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import beans.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/booking/")
public class BookingController {

    @Qualifier("bookingServiceImpl")
    @Autowired
    BookingService bookingService;

    @Qualifier("userServiceImpl")
    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @Qualifier("eventServiceImpl")
    @Autowired
    EventService eventService;


    @RequestMapping(value = "/price",method = RequestMethod.GET)
    public @ResponseBody Double getTicketPrice(@RequestParam("event") String event,
                                       @RequestParam("auditorium_name") String auditoriumName,
                                       @RequestParam("dateTime") LocalDateTime dateTime,
                                       @RequestParam("seats") List<Integer> seats,
                                       @RequestParam("user_id") Long userId){
        User user=userService.getById(userId);
        if(user==null){
            throw new MyObjectNotFoundException("User not found");
        }
        return bookingService.getTicketPrice(event,auditoriumName,dateTime,seats,user);
    }

    /**
     * Booking ticket
     * @param model
     * @param userId
     * @param event_id
     * @param seats
     * @return
     */
    @Transactional(rollbackFor = {MyObjectNotFoundException.class,MyNotEnoughMoneyException.class},propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public String bookTicket(Model model,@RequestParam("user_id") Long userId,
                                           @RequestParam("event_id") Long event_id,
                                           @RequestParam("seats") String seats){

        List<Integer> seatsList = Arrays.stream(seats.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

        User user=userService.getById(userId);

        if(user==null){
            throw new MyObjectNotFoundException("User not found "+userId);
        }

        Event event=eventService.getById(event_id);

        if(event==null){
            throw new MyObjectNotFoundException("Event not found "+event_id);
        }

        UserAccount userAccount=userAccountService.getById(user.getId());

        double totalPrice=event.getTicketPrice()*seatsList.size();
        double balance = userAccount.getBalance();

        if(balance <totalPrice){
            throw  new MyNotEnoughMoneyException("Ticket price is too high.\nUser has only "+ balance +" when ticket(s) costs"+totalPrice);
        }

        //gets money from user
        userAccountService.refill(userId,event.getTicketPrice());

        Ticket ticket=new Ticket(event,LocalDateTime.now(),seatsList,user,event.getTicketPrice());
        bookingService.bookTicket(user,ticket);
        return "redirect:/pdf/ticket/"+ticket.getId();
    }


    @RequestMapping(value = "/event/{event_id}",method = RequestMethod.GET)
    public String bookTicket(Model model, @PathVariable("event_id") long eventId){

        Event event=eventService.getById(eventId);

        if(event==null){
            throw new MyObjectNotFoundException("Event not found "+eventId);
        }

        List<User> users=userService.getAll();

        model.addAttribute("users",users);
        model.addAttribute("event_id",eventId);

        return "booking/booking";
    }
}
