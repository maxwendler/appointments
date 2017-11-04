package kickstart;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

@Component
public class StartTimeFormBacker {
    private String timeString;

    public Date getTime(){
        timeString = timeString + ":00";
        DateFormat df = DateFormat.getTimeInstance();
        Date date =  new Date();
        try {
            date = df.parse(timeString);
        }catch (ParseException e) {
            System.out.println("Date String not parsable: " + timeString );
        }
        return date;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
