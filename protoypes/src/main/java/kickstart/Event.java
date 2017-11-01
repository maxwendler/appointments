package kickstart;

import javafx.util.Pair;
import org.salespointframework.catalog.Product;

import java.util.*;

public class Event extends Product {

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

    public Collection<Appointment> getAppoinments (Date date){
        SortedSet<Appointment> appointmentsOnDate = new TreeSet<>();
        for (Appointment appointment : appointments){
            if ( (appointment.getStart().getTimeInMillis() <= date.getTime() && appointment.getEnd().getTimeInMillis() >= date.getTime()) ){
                appointmentsOnDate.add(appointment);
            }
        }
        return appointmentsOnDate;
    }
}