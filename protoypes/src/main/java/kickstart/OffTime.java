package kickstart;

public class OffTime extends Appointment{

    public enum Regularity {ONCE, DAILY, WEEKLY};
    private Regularity regularity;

    public OffTime (Regularity regularity, int year, int month, int dayOfMonth, int hourOfDay, int minute, int durationInHours){

        super(year, month, dayOfMonth, hourOfDay, minute,durationInHours);
        this.regularity = regularity;

    }

    public Regularity getRegularity() {
        return regularity;
    }
}
