package beans.controllers;

import beans.exceptions.BatchParseException;
import beans.exceptions.ObjectNotFoundException;
import beans.models.Auditorium;
import beans.models.Event;
import beans.models.Rate;
import beans.models.User;
import beans.services.AuditoriumService;
import beans.services.EventService;
import beans.services.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/batch/")
public class BatchController {
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    AuditoriumService auditoriumService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @RequestMapping(value = "/users/",method = RequestMethod.POST)
    public String batchUsers(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes) throws IOException {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }


        try {
            InputStream inputStream = file.getInputStream();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(
                    new InputStreamReader(inputStream, "UTF-8"));

            processBatchUsers(jsonObject);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (ParseException | IOException e) {
            throw new BatchParseException(e.getMessage());
        }

        return "redirect:/batch/uploadStatus";
    }


    @RequestMapping(value = "/events/",method = RequestMethod.POST)
    public String batchEvents(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes){
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            InputStream inputStream = file.getInputStream();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(
                    new InputStreamReader(inputStream, "UTF-8"));

            processBatchEvents(jsonObject);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException |ParseException  e) {
            throw new BatchParseException(e.getMessage());
        }
        return "redirect:/batch/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload_status";
    }

    private void processBatchUsers(JSONObject jsonObject) throws IOException, ParseException {
        JSONArray users= (JSONArray) jsonObject.get("users");

        for (Object user : users) {
            JSONObject userJson = (JSONObject) user;

            String email = (String) userJson.get("email");
            String name = (String) userJson.get("name");
            String birthday = (String) userJson.get("birthday");

            LocalDate parsedBirthday = LocalDate.parse(birthday, formatter);

            User newUser = new User(email, name, parsedBirthday);

            userService.register(newUser);
        }
    }

    private void processBatchEvents(JSONObject jsonObject) throws IOException, ParseException {
        JSONArray events= (JSONArray) jsonObject.get("events");

        for (Object event : events) {
            JSONObject eventJson = (JSONObject) event;

            String auditoriumName= (String) eventJson.get("auditorium_name");
            Auditorium auditorium =auditoriumService.getByName(auditoriumName);

            if(auditorium==null){
                throw new ObjectNotFoundException("Auditorium with name "+ auditoriumName+ " not found");
            }

            Rate rate = Rate.valueOf((String) eventJson.get("rate"));
            String name = (String) eventJson.get("name");
            Double basePrice = Double.valueOf((String) eventJson.get("base_price"));
            String dateTime = (String) eventJson.get("date_time");
            LocalDate parsedDateTime  = LocalDate.parse(dateTime , formatter);

            Event newEvent = new Event(name,rate,basePrice,parsedDateTime.atStartOfDay(),auditorium);

            eventService.create(newEvent);
        }
    }

}
