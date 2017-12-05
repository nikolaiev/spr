package beans.controllers;

import beans.exceptions.MyObjectNotFoundException;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.TicketService;
import beans.services.UserService;
import beans.util.PdfGeneratorUtil;
import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/pdf",method = RequestMethod.GET)
public class PdfController {
    private static String TEMPLATE_TICKETS="tickets";
    private static String TEMPLATE_SIMPLE_TICKET="ticket";

    @Autowired
    PdfGeneratorUtil generatorUtil;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;


    @RequestMapping(value = "/events",method = RequestMethod.GET,produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTicketsByEventsPDF(@RequestParam String event,
                                                        @RequestParam String auditorium,
                                                        @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")LocalDateTime date) throws Exception {

        System.out.println(event);
        System.out.println(auditorium);
        System.out.println(date);
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event, auditorium, date);



        Path pdfPath = generatorUtil.createPdf(TEMPLATE_TICKETS, new HashMap<String, List>() {{
            put("tickets", ticketsForEvent);
        }});

        byte[] contents = Files.readAllBytes(pdfPath);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }


    @RequestMapping(value = "/users",method = RequestMethod.GET,produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTicketsByEventsPDF() throws Exception {

        List<Ticket> ticketsForEvent = userService.getBookedTickets();

        Path pdfPath = generatorUtil.createPdf(TEMPLATE_TICKETS, new HashMap<String, List>() {{
            put("tickets", ticketsForEvent);
        }});

        byte[] contents = Files.readAllBytes(pdfPath);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket/{ticket_id}",method = RequestMethod.GET,produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTicketsById(@PathVariable("ticket_id") Long ticket_id) throws Exception {

        Ticket ticket= ticketService.getTicketById(ticket_id);

        if(ticket==null){
            throw new MyObjectNotFoundException("Ticket not found "+ticket_id);
        }

        Path pdfPath = generatorUtil.createPdf(TEMPLATE_SIMPLE_TICKET, new HashMap<String, Ticket>() {{
            put("ticket", ticket);
        }});

        byte[] contents = Files.readAllBytes(pdfPath);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{user_id}",method = RequestMethod.GET,produces=MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getTicketsByUserId(Model model, @PathVariable("user_id") long userId) throws DocumentException, TemplateException, IOException {

        User user=userService.getById(userId);

        if(user==null){
            throw new MyObjectNotFoundException("User not found "+userId);
        }

        List<Ticket> ticketsByUser = ticketService.getTicketsByUser(userId);

        Path pdfPath = generatorUtil.createPdf(TEMPLATE_TICKETS, new HashMap<String, List>() {{
            put("tickets", ticketsByUser);
        }});

        byte[] contents = Files.readAllBytes(pdfPath);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

}
