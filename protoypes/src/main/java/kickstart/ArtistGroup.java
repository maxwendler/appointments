package kickstart;


import java.util.*;

public class ArtistGroup {

    private String name;
    private Set<Show> shows = new TreeSet<Show>();
    private Set<Artist> artists = new TreeSet<Artist>();

    public ArtistGroup(String name){
        this.name = name;
    }

    public void addShow (Show show){
        shows.add(show);
    }

    public Collection<Appointment> getShowAppointments(Date date){
        SortedSet<Appointment> showAppointments = new TreeSet<>();
        for (Event show : shows){
            showAppointments.addAll(show.getAppoinments(date));
        }
        return showAppointments;
    }

    public Collection<Appointment> getAppointments(Date date){
        SortedSet<Appointment> times = new TreeSet<>();
        times.addAll(getShowAppointments(date));
        for (Artist artist : artists){
            times.addAll(artist.getPersonalAppointments(date));
        }
        return times;
    }
}


