package kickstart;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appointment implements Comparable<Appointment>{

    private GregorianCalendar start;
    private GregorianCalendar end;

    public Appointment(int year, int month, int dayOfMonth, int hourOfDay, int minute, int durationInHours){

        this.start = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        this.end = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        end.add(Calendar.HOUR_OF_DAY,durationInHours);
    }

    public int compareTo(Appointment a){
        return start.compareTo(a.start);
    }

    public GregorianCalendar getStart(){
        return start;
    }

    public GregorianCalendar getEnd(){
        return end;
    }

    public String getTime(){
        return start.get(Calendar.HOUR_OF_DAY) + ":" + start.get(Calendar.MINUTE) + " - " +
                end.get(Calendar.HOUR_OF_DAY) + ":" + end.get(Calendar.MINUTE);
    }

}
