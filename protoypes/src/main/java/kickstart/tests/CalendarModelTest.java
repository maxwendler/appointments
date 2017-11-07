package kickstart.tests;

import kickstart.CalendarModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarModelTest {

    CalendarModel calendarModel;

    @Before
    public void setUp() throws Exception {
        calendarModel = new CalendarModel();
    }

    @Test
    public void addToMonth() throws Exception {
        calendarModel.addToMonth(5);
        assertEquals(calendarModel.getMonthString(),"März");
        assertEquals(calendarModel.getYear(),2018);
    }

    @Test
    public void getYear() throws Exception {
        assertEquals(calendarModel.getYear(),2017);
    }

    @Test
    public void getMonth() throws Exception {
        assertEquals(calendarModel.getMonthString(),"Oktober");
    }

    @Test
    public void getWeeks() throws Exception {
        calendarModel.addToMonth(4);
        System.out.println(calendarModel.getWeeks().toString());
    }

}