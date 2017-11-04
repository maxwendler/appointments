package kickstart;

import javafx.util.Pair;
import org.salespointframework.catalog.Product;

import java.util.*;

public class Event implements Comparable<Event>{

    protected String name;
    protected int durationInHours;
    protected SortedSet<Appointment> appointments = new TreeSet<>();

    public Event(String name, int durationInHours) {
        this.name = name;
        this.durationInHours = durationInHours;
    }

    public String getName(){
        return name;
    }

    public void addAppointment(int year, int month, int dayOfMonth, int hourOfDay, int minute){
        appointments.add(new Appointment(year, month, dayOfMonth, hourOfDay, minute, durationInHours));
    }

    public Collection<Appointment> getAppointments (Date date){
        SortedSet<Appointment> appointmentsOnDate = new TreeSet<>();
        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();

        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY,0);
        start.set(Calendar.MINUTE,0);
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY,23);
        end.set(Calendar.MINUTE, 59);

        for (Appointment appointment : appointments){
            if ( (start.before(appointment.getStart()) && end.after(appointment.getStart())) ){
                appointmentsOnDate.add(appointment);
            }

        }
        return appointmentsOnDate;
    }

    public int compareTo(Event event){
        return this.name.compareTo(event.name);
    }
}