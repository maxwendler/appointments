package kickstart;

import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Appointment implements Comparable<Appointment>{

    protected GregorianCalendar start;
    protected GregorianCalendar end;

    public Appointment(int year, int month, int dayOfMonth, int hourOfDay, int minute, int durationInHours){

        this.start = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        this.end = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        end.add(Calendar.HOUR_OF_DAY,durationInHours);
    }

    public Appointment(Date date, int durationInHours){
        this.start = new GregorianCalendar();
        start.setTime(date);
        this.end = new GregorianCalendar();
        end.setTime(date);
        end.add(Calendar.HOUR_OF_DAY,durationInHours);
    }

    public int compareTo(Appointment a){
        return this.start.compareTo(a.start);
    }

    public GregorianCalendar getStart(){
        return start;
    }

    public GregorianCalendar getEnd(){
        return end;
    }

    public String toString(){
        DateFormat df = DateFormat.getTimeInstance();
        String startString = df.format(start.getTime());
        String endString = df.format(end.getTime());

        return startString + " - " + endString;
    }

}
