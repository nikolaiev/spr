package beans.controllers;


import beans.exceptions.ObjectNotFoundException;
import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/users/")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
    public String getUserById(Model model, @PathVariable("id") int id){

        User user=service.getById(id);

        if(user==null)
            throw new ObjectNotFoundException("User was not founded");

        model.addAttribute("user",user);

        return "user/simple_user";
    }

    @RequestMapping(value = "/email",method = RequestMethod.GET)
    public String getUserByEmail(Model model, @RequestParam("email") String email){

        User user=service.getUserByEmail(email);
        if(user==null)
            throw new ObjectNotFoundException("User was not founded");

        model.addAttribute("user",user);

        return "user/simple_user";
    }

    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public String getUsersByName(Model model, @RequestParam("name") String name){

        List<User> users = service.getUsersByName(name);
        model.addAttribute("users",users);

        return "user/users";
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String getUsersByName(Model model){

        List<User> users = service.getAll();
        model.addAttribute("users",users);

        return "user/users";
    }
}
