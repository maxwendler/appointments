package kickstart;

import javafx.util.Pair;
import org.salespointframework.catalog.Product;

import java.util.*;

public class Event extends Product {

    private String name;
    private SortedSet<Appointment> appointments = new TreeSet<>();

    public Event(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public SortedSet<Appointment> getAppointments(){
        return appointments;
    }

    public void addAppointment(int year, int month, int dayOfMonth, int hourOfDay, int minute, int durationInHours){
        appointments.add(new Appointment(year, month, dayOfMonth, hourOfDay, minute, durationInHours));
    }

    public Iterable<String> getTimes (){

        return null;
    }
}