import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Player {
    protected Game game;
    protected String name;
    protected ArrayList<Point> points = new ArrayList<Point>();
    protected Map<Point, ArrayList<Point>> steps = new HashMap<Point, ArrayList<Point>>();

    public abstract void printInfo();

    public abstract void checkPoint(int y, int x);

    public abstract ArrayList<Point> checkLine(int y, int x, int dy, int dx);

    public abstract int makeStep();
}
