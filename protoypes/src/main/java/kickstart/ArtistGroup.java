package kickstart;


import java.util.*;

public class ArtistGroup extends Organizer{

    private Set<Show> shows = new TreeSet<Show>();
    private Set<Artist> artists = new TreeSet<Artist>();

    public ArtistGroup(String name){
        super(name);
    }

    public void addShow (Show show){
        shows.add(show);
    }

    public Collection<Appointment> getShowAppointments(Date date){
        SortedSet<Appointment> showAppointments = new TreeSet<>();
        for (Event show : shows){
            showAppointments.addAll(show.getAppointments(date));
        }
        return showAppointments;
    }

    public Collection<Appointment> getAppointments(Date date){
        SortedSet<Appointment> appointments = new TreeSet<>();
        for (Artist artist : artists){
            appointments.addAll(artist.getAppointments(date));
        }
        return appointments;
    }
}


