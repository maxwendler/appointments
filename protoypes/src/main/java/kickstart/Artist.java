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
        SortedSet<Appointment> appointments = new TreeSet<>();
        appointments.addAll(getPersonalAppointments(date));
        for (ArtistGroup group : groups){
            appointments.addAll(group.getShowAppointments(date));
        }
        return appointments;
    }


    public Collection<Appointment> getPersonalAppointments (Date date){
        SortedSet<Appointment> personalAppointments = new TreeSet<>();

        for (Event workshop : workshops){
            personalAppointments.addAll(workshop.getAppoinments(date));
        }

        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();

        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY,0);
        start.set(Calendar.MINUTE,0);
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY,23);
        end.set(Calendar.MINUTE, 59);

        for (OffTime offTime : offTimes){
            switch (offTime.getRegularity()){
                case ONCE:
                    if ( start.before(offTime.getStart()) && end.after(offTime.getStart())){
                        personalAppointments.add(offTime);
                    }
                    break;
                case DAILY:
                    personalAppointments.add(offTime);
                    break;
                case WEEKLY:
                    double week = 6.048e+8;
                    if ((offTime.getStart().get(Calendar.DAY_OF_WEEK) == start.get(Calendar.DAY_OF_WEEK))) {
                        personalAppointments.add(offTime);
                    }
            }
        }
        return personalAppointments;
    }

    public void addWorkshop(Workshop w){
        workshops.add(w);
    }

    public void addOfftime(OffTime o){
        offTimes.add(o);
    }
}
