package kickstart;

import java.util.Set;
import java.util.TreeSet;

public class Show extends Event{

    private ArtistGroup organizer;

    public Show(String name, int durationInHours, ArtistGroup organizer){
        super(name,durationInHours);
        this.organizer = organizer;
    }

    public ArtistGroup getOrganizer() {
        return organizer;
    }
}
