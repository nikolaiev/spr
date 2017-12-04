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

    @RequestMapping(value = "/id/",method = RequestMethod.GET)
    public String getUserById(Model model, @RequestParam("id") int id){

        User user=service.getById(id);

        if(user==null)
            throw new ObjectNotFoundException("User was not founded");

        model.addAttribute("user",user);

        return "user/simple_user";
    }

    @RequestMapping(value = "/email/",method = RequestMethod.GET)
    public String getUserByEmail(Model model, @RequestParam("email") String email){

        User user=service.getUserByEmail(email);
        if(user==null)
            throw new ObjectNotFoundException("User was not founded");

        model.addAttribute("user",user);

        return "user/simple_user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsersByName(Model model, @RequestParam("name") String id){

        List<User> users = service.getUsersByName(id);
        model.addAttribute("users",users);

        return "user/users";
    }
}
