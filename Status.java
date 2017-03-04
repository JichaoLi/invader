import java.util.ArrayList;
import java.util.HashSet;

public class Status {
	public double x;
	public double y;
	public double dx;
	public double dy;
	public ArrayList<Point> mines;
	public HashSet<Point> capturedByOthers;
	public ArrayList<Point> players;
	public ArrayList<Point> bombs;
}
