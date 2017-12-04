package beans.controllers;

import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    BookingService service;

    @RequestMapping(value = "/price",method = RequestMethod.GET)
    public @ResponseBody Double getTicketPrice(@RequestParam("event") String event,
                                       @RequestParam("auditorium") String auditorium,
                                       @RequestParam("dateTime") LocalDateTime dateTime,
                                       @RequestParam("seats") List<Integer> seats,
                                       @RequestParam("user") User user){
        return service.getTicketPrice(event,auditorium,dateTime,seats,user);
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public @ResponseBody Ticket bookTicket(@RequestParam("user") User user,
                           @RequestParam("ticket") Ticket ticket){
        return service.bookTicket(user,ticket);
    }


}
