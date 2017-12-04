package beans.controllers;

import beans.exceptions.ObjectNotFoundException;
import beans.models.Auditorium;
import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(value = "/events/")
public class EventController {
    @Autowired
    EventService service;

    @RequestMapping(value = "/single",method = RequestMethod.GET)
    public String getEvent(Model model, @RequestParam  String name, @RequestParam Auditorium auditorium,
                           @RequestParam LocalDateTime dateTime){
        Event event=service.getEvent(name, auditorium, dateTime);

        if(event==null)
            throw new ObjectNotFoundException("Event was not founded");

        model.addAttribute("event",event);

        return "event/semple_event";
    }

    @RequestMapping(value = "/name/",method = RequestMethod.GET)
    public String getByName(Model model, @RequestParam  String name){
        List<Event> events = service.getByName(name);
        model.addAttribute("events",events);

        return "event/events";
    }

    @RequestMapping(value = "/all/",method = RequestMethod.GET)
    public String getAll(Model model){
        List<Event> events = service.getAll();
        model.addAttribute("events",events);

        return "event/events";
    }

    @RequestMapping(value = "/period/",method = RequestMethod.GET,params = {"from","to"})
    public String getEvent(Model model, @RequestParam LocalDateTime from,@RequestParam LocalDateTime to){
        List<Event> events = service.getForDateRange(from, to);
        model.addAttribute("events",events);

        return "event/events";
    }

    @RequestMapping(value = "/until",method = RequestMethod.GET,params = "to")
    public String getNextEvents(Model model, @RequestParam LocalDateTime to){
        List<Event> events = service.getNextEvents(to);
        model.addAttribute("events",events);

        return "event/events";
    }
}
