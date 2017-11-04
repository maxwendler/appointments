package kickstart.controller;

import kickstart.*;
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
        Artist fridolin = new Artist("Fridolin");
        Workshop w1 = new Workshop("Jonglieren auf zwei Beinen",2,1,fridolin);
        Workshop w2 = new Workshop("Jonglieren auf zwei Füßen",3,4,fridolin);
        OffTime o1 = new OffTime(OffTime.Regularity.DAILY,2017,10,6,0,0,12);

        fridolin.addWorkshop(w1);
        fridolin.addWorkshop(w2);

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
            model.addAttribute("appointments",event.getOrganizer().getAppointments(date));
        }

        return "book";
    }
}
