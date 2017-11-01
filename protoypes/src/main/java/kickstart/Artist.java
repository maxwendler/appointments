package kickstart;

import java.util.*;

public class Artist {

    private String name;

    private SortedSet<OffTime> offTimes = new TreeSet<OffTime>();
    private SortedSet<Workshop> workshops = new TreeSet<>();
    private Set<ArtistGroup> groups = new HashSet<>();

    public Artist(String name){
        this.name = name;
    }

    public Iterable<Appointment> getAppointments (Date date){
        SortedSet<Appointment> times = new TreeSet<>();
        times.addAll(getPersonalAppointments(date));
        for (ArtistGroup group : groups){
            times.addAll(group.getShowAppointments(date));
        }
        return times;
    }


    public Collection<Appointment> getPersonalAppointments (Date date){
        SortedSet<Appointment> personalAppointments = new TreeSet<>();
        for (Event workshop : workshops){
            personalAppointments.addAll(workshop.getAppoinments(date));
        }
        for (OffTime offTime : offTimes){
            switch (offTime.getRegularity()){
                case ONCE:
                    if ( (offTime.getStart().getTimeInMillis() <= date.getTime()
                            && offTime.getEnd().getTimeInMillis() >= date.getTime()) ){
                        personalAppointments.add(offTime);
                    }
                    break;
                case DAILY:
                    double day = 8.64e+7;
                    if ( (offTime.getStart().getTimeInMillis() % day <= date.getTime() % day
                            && offTime.getEnd().getTimeInMillis() % day >= date.getTime() % day) ) {
                        personalAppointments.add(offTime);
                    }
                    break;
                case WEEKLY:
                    double week = 6.048e+8;
                    if ( (offTime.getStart().getTimeInMillis() % week <= date.getTime() % week
                            && offTime.getEnd().getTimeInMillis() % week >= date.getTime() % week) ) {
                        personalAppointments.add(offTime);
                    }
            }
        }
        return personalAppointments;
    }
}
