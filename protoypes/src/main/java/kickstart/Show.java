package kickstart;

import java.util.Set;
import java.util.TreeSet;

public class Show extends Event{


    public Show(String name, int durationInHours, ArtistGroup organizer){
        super(name,durationInHours,organizer);
    }

    public Organizer getOrganizer() {
        return organizer;
    }
}
