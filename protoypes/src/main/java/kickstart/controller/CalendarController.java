package kickstart.controller;

import kickstart.CalendarModel;
import kickstart.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

@Controller
public class CalendarController {

    private final CalendarModel calendarModel;
    private Map<String , Event> eventCatalog = new TreeMap<>();

    @Autowired
    public CalendarController (CalendarModel calendarModel){

        this.calendarModel = calendarModel;

        Event ex1 = new Event("Fliegen lernen mit Fridolin",2);
        ex1.addAppointment(2017,10,5,13,30);
        eventCatalog.put("e0001",ex1);

    }

    @RequestMapping("/calendar")
    public String calendar(@RequestParam("eventId") Optional<String> eventId, @RequestParam("month") Optional<String> monthSummand, Model model) {

        calendarModel.addToMonth(Integer.valueOf(monthSummand.orElse("0")));

        model.addAttribute("year", calendarModel.getYear());
        model.addAttribute("month",calendarModel.getMonth());
        model.addAttribute("weeks",calendarModel.getWeeks());

        if (eventId.isPresent()) {
            Event event = eventCatalog.getOrDefault(eventId.get(), null);
            if (event != null) {
                model.addAttribute("eventName", event.getName());
                model.addAttribute("eventId",eventId.get());
            }else{
                model.addAttribute("eventName","<EventNotFound>");
            }
        }else{
            model.addAttribute("eventName","<NoEventId>");
        }

        return "calendar";
    }

    @RequestMapping("/book")
    public String book(@RequestParam("eventId") Optional<String> eventId, @RequestParam("date") Optional<String> dateString, Model model){

        if (!(eventId.isPresent() && dateString.isPresent())){
            return "bookMissingParams";
        }

        Event event = eventCatalog.getOrDefault(eventId.get(),null);

        DateFormat df = DateFormat.getDateInstance();
        Date date =  new Date();
        try {
            date = df.parse(dateString.get());
        }catch (ParseException e) {
            System.out.println("Date String not parsable: " + dateString.get() );
        }

        if (event == null){
            model.addAttribute("eventName","<EventNotFound>");
        }else{
            model.addAttribute("eventName",event.getName());
            model.addAttribute("date",dateString);
            model.addAttribute("appointments",event.getAppoinments(date));
        }

        return "book";
    }
}
