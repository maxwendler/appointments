package kickstart.controller;

import kickstart.CalendarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Controller
public class CalendarController {

    private final CalendarModel calendarModel;

    @Autowired
    public CalendarController (CalendarModel calendarModel){
        this.calendarModel = calendarModel;
    }

    @RequestMapping("/calendar")
    public String calendar(@RequestParam("eventId") Optional<String> eventId, @RequestParam("month") Optional<String> monthSummand, Model model) {

        calendarModel.addToMonth(Integer.valueOf(monthSummand.orElse("0")));

        model.addAttribute("year", calendarModel.getYear());
        model.addAttribute("month",calendarModel.getMonth());
        model.addAttribute("weeks",calendarModel.getWeeks());

        return "calendar";
    }
}
