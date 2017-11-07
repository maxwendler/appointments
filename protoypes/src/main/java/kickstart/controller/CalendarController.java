package kickstart.controller;

import kickstart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
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
        w1.addAppointment(2017,10,6,13,30);
        Workshop w2 = new Workshop("Jonglieren auf zwei Füßen",3,4,fridolin);
        w2.addAppointment(2017,10,6,16,0);
        OffTime o1 = new OffTime(OffTime.Regularity.DAILY,2017,10,6,0,0,12);

        fridolin.addWorkshop(w1);
        fridolin.addWorkshop(w2);
        fridolin.addOfftime(o1);

        eventCatalog.put("w1",w1);
        eventCatalog.put("w2",w2);

    }

    @RequestMapping("/calendar")
    public String calendar(@RequestParam("eventId") Optional<String> eventId, @RequestParam("month") int monthSummand, Model model) {

        CalendarModel calendarModel = new CalendarModel();
        calendarModel.addToMonth(monthSummand);

        model.addAttribute("year", calendarModel.getYear());
        model.addAttribute("monthSummand",monthSummand);
        model.addAttribute("monthString",calendarModel.getMonthString());
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
            model.addAttribute("eventId",eventId.get());
            model.addAttribute("date",dateString.get());
            model.addAttribute("appointments",event.getOrganizer().getAppointments(date));
            model.addAttribute("duration",event.getDurationInHours());
            model.addAttribute("formBacker", new StartTimeFormBacker());
        }

        System.out.println(event.getOrganizer().getAppointments(date));

        return "book";
    }

    @PostMapping("/newAppointment")
    public String newAppointment(@RequestParam("eventId") Optional<String> eventId, @RequestParam("date") Optional<String> dateString, @ModelAttribute StartTimeFormBacker formBacker, Model model){

        Event event = eventCatalog.get(eventId.get());
        Date time = formBacker.getTime();

        DateFormat df = DateFormat.getDateInstance();
        Date date =  new Date();
        try {
            date = df.parse(dateString.get());
        }catch (ParseException e) {
            System.out.println("Date String not parsable: " + dateString.get() );
        }

        boolean isValid = true;
        double day = 8.64e+7;
        int hour = 3600000;

        System.out.println(time.getTime() % day);

        for(Appointment appointment : event.getOrganizer().getAppointments(date)){
            if (isValid){
                boolean isStartBlocked = ((appointment.getStart().getTimeInMillis() + hour) % day <= (time.getTime() + hour) % day) && ((appointment.getEnd().getTimeInMillis() + hour) % day >= (time.getTime() + hour) % day);
                boolean isEndBlocked = ((appointment.getStart().getTimeInMillis() + hour) % day <= (time.getTime() + hour * (1 + event.getDurationInHours())) % day) && ((appointment.getEnd().getTimeInMillis() + hour) % day >= (time.getTime() + hour) % day);
                if (isStartBlocked || isEndBlocked){
                    isValid = false;
                }
            }else{
                break;
            }
        }

        if (isValid) {
            event.addAppointment(date);
            return "redirect:booking";
        }else{
            return "redirect:unvalidBooking?eventId="+eventId.get() + "&date=" +dateString.get();
        }
    }

    @RequestMapping("/booking")
    public String booking(){
        return "booking";
    }

    @RequestMapping("/unvalidBooking")
    public String unvalidBooking(@RequestParam("eventId") Optional<String> eventId, @RequestParam("date") Optional<String> date, Model model){
        model.addAttribute("eventId",eventId.get());
        model.addAttribute("date",date.get());

        return "unvalidBooking";
    }

    @RequestMapping("/appointment")
    public String appointment(@RequestParam("eventId") Optional<String> eventId, @RequestParam("appointmentId") Optional<String> appointmentId, Model model){
        Event event = eventCatalog.get(eventId.get());
        Map<String, Appointment> appointments = new TreeMap<>();
        //Muss halt alles noch alles auf Map umgestellt werden, yay.
        appointments.put("a1",new Appointment(2017,10,6,13,30,3));
        Appointment appointment = appointments.get(appointmentId.get());

        DateFormat df = DateFormat.getDateInstance();

        model.addAttribute("appointment", appointment);
        model.addAttribute("event", event);
        model.addAttribute("date", df.format(appointment.getStart().getTime()));

        return "appointment";
    }
}
