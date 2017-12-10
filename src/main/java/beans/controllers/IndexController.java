package beans.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home() {
            return "home";
    }

    @RequestMapping("/upload_batch")
    public String batch() {
        return "upload_batch";
    }
}
