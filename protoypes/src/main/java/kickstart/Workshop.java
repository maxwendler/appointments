package kickstart;

public class Workshop extends Event{

    private enum Type {SINGLEDAY, MULTIDAY}
    Type type;

    public Type getType() {
        return type;
    }

}
