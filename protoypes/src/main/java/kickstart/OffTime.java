package kickstart;

import java.util.GregorianCalendar;

public class OffTime {

    private enum Regularity {ONCE, DAILY, WEEKLY};
    Regularity regularity;
    private GregorianCalendar date;
    private int duration; // in hours

    public OffTime (Regularity regularity, int year, int month, int dayOfMonth, int hourOfDay, int minute, int duration){

        this.regularity = regularity;
        this.date = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);
        this.duration = duration;

    }

    public Regularity getRegularity() {
        return regularity;
    }
}
