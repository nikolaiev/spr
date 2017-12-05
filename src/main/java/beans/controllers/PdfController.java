package beans.controllers;

import beans.models.Ticket;
import beans.services.BookingService;
import beans.services.UserService;
import beans.util.PdfGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/pdf",method = RequestMethod.GET)
public class PdfController {
    private static String TEMPLATE_TICKETS="tickets";

    @Autowired
    PdfGeneratorUtil generatorUtil;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;


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

}
