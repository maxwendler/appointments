package kickstart;

import org.salespointframework.catalog.Product;

import java.util.GregorianCalendar;
import java.util.SortedSet;
import java.util.TreeSet;

public class Event extends Product{

    private int duration; //in hours

    private SortedSet<GregorianCalendar> appointments = new TreeSet<GregorianCalendar>();



}
