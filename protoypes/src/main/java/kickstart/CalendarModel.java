package kickstart;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class CalendarModel {

    private static GregorianCalendar calendar;

    public class Week{
        public Day[] days = {null,null,null,null,null,null,null};

        public String toString(){
            String string = "";
            for (int i = 0; i <= 6; i++){
                string = string + (i+1) + " : " + days[i] +" ;\n";
            }
            return string;
        }
    }

    private class Day{
        private GregorianCalendar calendar;

        public Day (int year, int month, int dayOfMonth){
            this.calendar = new GregorianCalendar(year, month, dayOfMonth);
        }

        public int getDayOfMonth(){
            return calendar.get(Calendar.DAY_OF_MONTH);
        }

        public String getDate(){
            DateFormat df = DateFormat.getDateInstance();
            return df.format(calendar.getTime());
        }
    }

    public CalendarModel(){
        calendar = new GregorianCalendar();
    }

    public void addToMonth (int monthSummand){
        calendar.add(Calendar.MONTH, monthSummand);
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    public String getMonthString(){
        String monthString;
        switch (calendar.get(Calendar.MONTH)) {
            case 0:
                monthString = "Januar";
                break;
            case 1:
                monthString = "Februar";
                break;
            case 2:
                monthString = "März";
                break;
            case 3:
                monthString = "April";
                break;
            case 4:
                monthString = "Mai";
                break;
            case 5:
                monthString = "Juni";
                break;
            case 6:
                monthString = "Juli";
                break;
            case 7:
                monthString = "August";
                break;
            case 8:
                monthString = "September";
                break;
            case 9:
                monthString = "Oktober";
                break;
            case 10:
                monthString = "November";
                break;
            case 11:
                monthString = "Dezember";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    public List<Week> getWeeks(){
        GregorianCalendar operatingCalendar =
                new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        operatingCalendar.set(Calendar.DAY_OF_MONTH, 1);

        List<Week> weeks = new ArrayList<>();

        for (int i = 0; i <
        operatingCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH); i++){
            Week week = new Week();

            while (operatingCalendar.get(Calendar.WEEK_OF_MONTH) == i){
                week.days[(operatingCalendar.get(Calendar.DAY_OF_WEEK) + 5) % 7] =
                        new Day(operatingCalendar.get(Calendar.YEAR), operatingCalendar.get(Calendar.MONTH),
                        operatingCalendar.get(Calendar.DAY_OF_MONTH));
                operatingCalendar.add(Calendar.DAY_OF_MONTH,1);
            }
            weeks.add(week);
        }

        Week weekInNextMonth = new Week();

        while (operatingCalendar.get(Calendar.DAY_OF_MONTH) != 1){
            weekInNextMonth.days[(operatingCalendar.get(Calendar.DAY_OF_WEEK) + 5) % 7] =
                    new Day(operatingCalendar.get(Calendar.YEAR), operatingCalendar.get(Calendar.MONTH),
                    operatingCalendar.get(Calendar.DAY_OF_MONTH));;
            operatingCalendar.add(Calendar.DAY_OF_MONTH,1);
        }
        weeks.add(weekInNextMonth);

        return weeks;
    }
}
