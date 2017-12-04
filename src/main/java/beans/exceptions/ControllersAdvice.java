package beans.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllersAdvice{

    @ExceptionHandler(ObjectNotFoundException.class)
    public String showErrorPage(Model model,Exception e){
        model.addAttribute("message",e.getMessage());

        return "exception";
    }
}
