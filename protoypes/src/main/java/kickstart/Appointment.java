package kickstart;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appointment {

    private GregorianCalendar date;
    private int durationInHours;

    public Appointment(int year, int month, int dayOfMonth, int hourOfDay, int minute, int durationInHours){

        this.date = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        this.durationInHours = durationInHours;

    }

    public GregorianCalendar getStart(){
        return date;
    }

    public GregorianCalendar getEnd(){
        GregorianCalendar endDate = date;
        endDate.add(Calendar.HOUR_OF_DAY, durationInHours);
        return endDate;
    }
}
