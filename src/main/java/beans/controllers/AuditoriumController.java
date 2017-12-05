package beans.controllers;

import beans.exceptions.MyObjectNotFoundException;
import beans.models.Auditorium;
import beans.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("/auditoriums/")
public class AuditoriumController {

    @Autowired
    AuditoriumService service;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String getAuditoriums(Model model){
        List<Auditorium> auditoriums = service.getAuditoriums();
        model.addAttribute("auditoriums",auditoriums );

        return "auditorium/auditoriums";
    }

    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public String getByName(Model model, @RequestParam String name){
        Auditorium auditorium = service.getByName(name);

        if(auditorium==null)
            throw new MyObjectNotFoundException("Auditorium was not founded");

        model.addAttribute("auditorium",auditorium );

        return "auditorium/simple_auditorium";
    }
}
