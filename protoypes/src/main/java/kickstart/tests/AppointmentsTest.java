package kickstart.tests;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import kickstart.*;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class AppointmentsTest {

    private Artist artist = new Artist("Fridolin");
    private ArtistGroup artistGroup = new ArtistGroup("Freundliche Follidioten");
    private GregorianCalendar calendar;

    @Before
    public void setUp() throws Exception{
        Show s1 = new Show("Die Freiheit flieht!",1,artistGroup);
        Show s2 = new Show("Fröhliches Frohlocken",2,artistGroup);
        s1.addAppointment(2017,11,1,10,0);
        s2.addAppointment(2017,11,1,11,30);
        artistGroup.addShow(s2);
        artistGroup.addShow(s1);

        Workshop w1 = new Workshop("Fliegen lernen mit Fridolin", 1,1,artist);
        Workshop w2 = new Workshop("Jonglieren",1,1,artist);
        w1.addAppointment(2017,11,1,14,0);
        w2.addAppointment(2017,11,1,15,30);
        artist.addWorkshop(w2);
        artist.addWorkshop(w1);

        OffTime o1 = new OffTime(OffTime.Regularity.DAILY,2017,11,1,18,0,6);
        OffTime o2 = new OffTime(OffTime.Regularity.WEEKLY,2017,11,2,15,30,2);
        artist.addOfftime(o1);
        artist.addOfftime(o2);

        calendar = new GregorianCalendar(2017,11,1);
    }

    @Test
    public void artistAppointmentsTest(){
        for (Appointment appointment : artist.getAppointments(calendar.getTime())){
            System.out.println(appointment.toString());
        }
    }

    @Test
    public void groupAppointmentsTest(){
        for (Appointment appointment : artistGroup.getAppointments(calendar.getTime())){
            System.out.println(appointment.toString());
        }
    }
}
