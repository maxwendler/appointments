package kickstart;

import java.util.Collection;
import java.util.Date;

public abstract class Organizer {
    private String name;

    public Organizer(String name){
        this.name=name;
    }

    public abstract Collection<Appointment> getAppointments(Date date);
}
