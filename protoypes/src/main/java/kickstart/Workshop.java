package kickstart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workshop extends Event{

    private int durationInDays;
    private Artist organizer;

    public Workshop(String name, int durationInHours, int durationInDays, Artist organizer){
        super(name,durationInHours);
        this.organizer = organizer;
        this.durationInDays = durationInDays;
    }

    public void addAppointment(int year, int month, int dayOfMonth, int hourOfDay, int minute, int durationInHours) {
        for (int i = 0; i < durationInDays; i++) {
            appointments.add(new Appointment(year, month, dayOfMonth + i, hourOfDay, minute, durationInHours));
        }
    }

    public Artist getOrganizer() {
        return organizer;
    }
}
